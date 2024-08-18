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

package dev.shiza.juliet.hikari;

import com.zaxxer.hikari.HikariDataSource;
import dev.shiza.juliet.datasource.PooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

class HikariPooledDataSource implements PooledDataSource {

  private final HikariDataSource underlyingDataSource;

  HikariPooledDataSource(final HikariDataSource underlyingDataSource) {
    this.underlyingDataSource = underlyingDataSource;
  }

  @Override
  public Connection borrowConnection() throws SQLException {
    return underlyingDataSource.getConnection();
  }

  @Override
  public void close() {
    underlyingDataSource.close();
  }
}
