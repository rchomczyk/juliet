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

import org.testcontainers.containers.MySQLContainer;

public final class HikariPooledDatasourceUtils {

  private HikariPooledDatasourceUtils() {

  }

  public static PooledDataSource produceHikariDataSourceByContainer(MySQLContainer<?> container) {
    return HikariPooledDataSourceFactory.produceHikariDataSource(HikariConfigBuilder.newBuilder()
        .withJdbcUri(format("jdbc:mysql://%s:%d/%s",
            container.getHost(), container.getFirstMappedPort(), container.getDatabaseName()))
        .withUsername(container.getUsername())
        .withPassword(container.getPassword())
        .build());
  }
}
