/*
 *    Copyright 2023-2024 juliet
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

package moe.rafal.juliet.datasource.hikari;

import com.zaxxer.hikari.HikariConfig;
import java.util.Properties;

public final class HikariConfigBuilder {

  private String driverClassName;
  private String jdbcUri;
  private String username;
  private String password;
  private Properties dataSourceProperties = new Properties();

  private HikariConfigBuilder() {}

  public static HikariConfigBuilder newBuilder() {
    return new HikariConfigBuilder();
  }

  public HikariConfigBuilder withDriverClassName(final String driverClassName) {
    this.driverClassName = driverClassName;
    return this;
  }

  public HikariConfigBuilder withJdbcUri(final String jdbcUri) {
    this.jdbcUri = jdbcUri;
    return this;
  }

  public HikariConfigBuilder withUsername(final String username) {
    this.username = username;
    return this;
  }

  public HikariConfigBuilder withPassword(final String password) {
    this.password = password;
    return this;
  }

  public HikariConfigBuilder withDatasourceProperty(final String key, final String value) {
    dataSourceProperties.setProperty(key, value);
    return this;
  }

  public HikariConfigBuilder withDatasourceProperties(final Properties dataSourceProperties) {
    this.dataSourceProperties = dataSourceProperties;
    return this;
  }

  public HikariConfig build() throws HikariConfigBuildException {
    if (jdbcUri == null) {
      throw new HikariConfigBuildException(
          "Hikari config could not be built, without specifying jdbc uri, which is required.");
    }

    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(jdbcUri);

    if (username != null) {
      hikariConfig.setUsername(username);
    }

    if (password != null) {
      hikariConfig.setPassword(password);
    }

    if (driverClassName != null) {
      hikariConfig.setDriverClassName(driverClassName);
    }

    hikariConfig.setDataSourceProperties(dataSourceProperties);
    return hikariConfig;
  }
}
