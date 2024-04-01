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

package moe.rafal.juliet;

import moe.rafal.juliet.datasource.PooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class JulietImpl implements Juliet {

  private final PooledDataSource dataSource;

  JulietImpl(final PooledDataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Connection borrowConnection() throws SQLException {
    return dataSource.borrowConnection();
  }

  @Override
  public void close() {
    dataSource.close();
  }
}
