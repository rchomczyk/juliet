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

package moe.rafal.juliet;

import static moe.rafal.juliet.datasource.HikariPooledDatasourceUtils.produceHikariDataSourceByContainer;

import org.testcontainers.containers.MySQLContainer;

final class JulietUtils {

  private JulietUtils() {

  }

  public static Juliet produceJulietByContainer(MySQLContainer<?> container) {
    return JulietBuilder.newBuilder()
        .withDataSource(produceHikariDataSourceByContainer(container))
        .build();
  }
}