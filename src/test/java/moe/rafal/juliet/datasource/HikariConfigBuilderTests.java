/*
 *    Copyright 2023 juliet
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package moe.rafal.juliet.datasource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.zaxxer.hikari.HikariConfig;
import org.junit.jupiter.api.Test;

class HikariConfigBuilderTests {

  private static final String VALID_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
  private static final String VALID_JDBC_URI = "jdbc:mysql://127.0.0.1:3306/juliet_test";
  private static final String VALID_USERNAME = "shitzuu";
  private static final String VALID_PASSWORD = "my-secret-password-123";

  @Test
  void verifyThrownExceptionWhenBuildingHikariConfigWithoutJdbcUriTest() {
    assertThatCode(() -> HikariConfigBuilder.newBuilder().build())
        .isInstanceOf(HikariConfigBuildException.class)
        .hasMessage(
            "Hikari config could not be built, without specifying jdbc uri, which is required.");
  }

  @Test
  void verifyBuiltHikariConfigWithSpecifyingUsernameAndPasswordTest() {
    assertThat(HikariConfigBuilder.newBuilder()
        .withJdbcUri(VALID_JDBC_URI)
        .withUsername(VALID_USERNAME)
        .withPassword(VALID_PASSWORD)
        .build())
        .extracting(
            HikariConfig::getJdbcUrl,
            HikariConfig::getUsername,
            HikariConfig::getPassword)
        .containsExactly(VALID_JDBC_URI, VALID_USERNAME, VALID_PASSWORD);
  }

  @Test
  void verifyBuiltHikariConfigWithoutSpecifyingUsernameTest() {
    assertThat(HikariConfigBuilder.newBuilder()
        .withJdbcUri(VALID_JDBC_URI)
        .withPassword(VALID_PASSWORD)
        .build())
        .extracting(
            HikariConfig::getJdbcUrl,
            HikariConfig::getUsername,
            HikariConfig::getPassword)
        .containsExactly(VALID_JDBC_URI, null, VALID_PASSWORD);
  }

  @Test
  void verifyBuiltHikariConfigWithoutSpecifyingPasswordTest() {
    assertThat(HikariConfigBuilder.newBuilder()
        .withJdbcUri(VALID_JDBC_URI)
        .withUsername(VALID_USERNAME)
        .build())
        .extracting(
            HikariConfig::getJdbcUrl,
            HikariConfig::getUsername,
            HikariConfig::getPassword)
        .containsExactly(VALID_JDBC_URI, VALID_USERNAME, null);
  }

  @Test
  void verifyBuiltHikariConfigWithoutBothUsernameAndPasswordTest() {
    assertThat(HikariConfigBuilder.newBuilder()
        .withJdbcUri(VALID_JDBC_URI)
        .build())
        .extracting(
            HikariConfig::getJdbcUrl,
            HikariConfig::getUsername,
            HikariConfig::getPassword)
        .containsExactly(VALID_JDBC_URI, null, null);
  }

  @Test
  void verifyBuiltHikariConfigWithSpecifyingDriverClassNameTest() {
    assertThat(HikariConfigBuilder.newBuilder()
        .withDriverClassName(VALID_DRIVER_CLASS_NAME)
        .withJdbcUri(VALID_JDBC_URI)
        .build())
        .extracting(HikariConfig::getJdbcUrl, HikariConfig::getDriverClassName)
        .containsExactly(VALID_JDBC_URI, VALID_DRIVER_CLASS_NAME);
  }

  @Test
  void verifyBuiltHikariConfigWithoutSpecifyingDriverClassNameTest() {
    assertThat(HikariConfigBuilder.newBuilder()
        .withJdbcUri(VALID_JDBC_URI)
        .build())
        .extracting(HikariConfig::getJdbcUrl, HikariConfig::getDriverClassName)
        .containsExactly(VALID_JDBC_URI, null);
  }
}
