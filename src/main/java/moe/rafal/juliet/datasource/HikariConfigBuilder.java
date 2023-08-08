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

import com.zaxxer.hikari.HikariConfig;

public final class HikariConfigBuilder {

  private String jdbcUri;
  private String username;
  private String password;

  private HikariConfigBuilder() {

  }

  public static HikariConfigBuilder newBuilder() {
    return new HikariConfigBuilder();
  }

  public HikariConfigBuilder withJdbcUri(String jdbcUri) {
    this.jdbcUri = jdbcUri;
    return this;
  }

  public HikariConfigBuilder withUsername(String username) {
    this.username = username;
    return this;
  }

  public HikariConfigBuilder withPassword(String password) {
    this.password = password;
    return this;
  }

  public HikariConfig build() throws HikariConfigBuildException {
    if (jdbcUri == null) {
      throw new HikariConfigBuildException(
          "Hikari config could not be built, without specifying jdbc uri, which is required.");
    }

    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(jdbcUri);

    final boolean whetherUsernameIsSpecified = username != null;
    if (whetherUsernameIsSpecified) {
      hikariConfig.setUsername(username);
    }

    final boolean whetherPasswordIsSpecified = password != null;
    if (whetherPasswordIsSpecified) {
      hikariConfig.setPassword(password);
    }

    return hikariConfig;
  }
}