# csc365WebAppExample

### To Configure Datasource with Tomcat
1. Create META-INF directory under webapp directory
2. Create context.xml file in the META-INF directory
3. Add the following xml in the context.xml file
<?xml version="1.0" encoding="UTF-8"?>
        <!--
          Licensed to the Apache Software Foundation (ASF) under one or more
          contributor license agreements.  See the NOTICE file distributed with
          this work for additional information regarding copyright ownership.
          The ASF licenses this file to You under the Apache License, Version 2.0
          (the "License"); you may not use this file except in compliance with
          the License.  You may obtain a copy of the License at

              http://www.apache.org/licenses/LICENSE-2.0

          Unless required by applicable law or agreed to in writing, software
          distributed under the License is distributed on an "AS IS" BASIS,
          WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
          See the License for the specific language governing permissions and
          limitations under the License.
        -->
        <!-- The contents of this file will be loaded for each web application -->
<Context>

    <!-- Default set of monitored resources. If one of these changes, the    -->
    <!-- web application will be reloaded.                                   -->
    <!-- Uncomment this to disable session persistence across Tomcat restarts -->
    <!--
    <Manager pathname="" />
    -->
    <Resource name="jdbc/xyz" auth="Container" type="javax.sql.DataSource"
              maxTotal="100" maxIdle="30" maxWaitMillis="10000"
              username="xxx" password="yyy" driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/xyz" factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"/>

</Context>

4. Change xyz to an appropriate database name
5. Change xxx and yyy to appropriate user and password for the database
6. Change localhost to an appropriate server url
