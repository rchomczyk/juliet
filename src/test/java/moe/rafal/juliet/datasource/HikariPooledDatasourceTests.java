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

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.utility.DockerImageName.parse;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class HikariPooledDatasourceTests {

  @Container
  private final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(parse("mysql:latest"))
      .withReuse(true);

  @Test
  void verifyWhetherContainerIsRunningTest() {
    assertThat(mySQLContainer.isRunning())
        .isTrue();
  }

  @Test
  void verifyWhetherConnectionWasResolvedSuccessfullyTest() throws SQLException {
    try (Connection connection = getHikariDataSource().borrowConnection()) {
      assertThat(connection).isNotNull();
    }
  }

  private PooledDataSource getHikariDataSource() {
    return HikariPooledDataSourceFactory.produceHikariDataSource(HikariConfigBuilder.newBuilder()
        .withJdbcUri(format("jdbc:mysql://%s:%d/%s",
            mySQLContainer.getHost(),
            mySQLContainer.getFirstMappedPort(),
            mySQLContainer.getDatabaseName()))
        .withUsername(mySQLContainer.getUsername())
        .withPassword(mySQLContainer.getPassword())
        .build());
  }
}
