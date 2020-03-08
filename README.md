# csc365WebAppExample

### To Set up this example
1. clone this repo, or download it as a zip file and extract it on your computer.
2. Open the root directory of this repo with your favorite IDE. With IntelliJ, for example, just click "open" on the main menu and select the directory.

### To Configure Datasource with Tomcat
1. Create META-INF directory under webapp directory
2. Create context.xml file in the META-INF directory
3. Add the following xml in the context.xml file
```
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
              url="jdbc:mysql://localhost:3306/xyz"/>

</Context>
```
4. Change xyz to an appropriate database name
5. Change xxx and yyy to appropriate user and password for the database
6. Change localhost to an appropriate server url

### Tables Required in your MySQL database ###
1. Create Customer table
```
CREATE TABLE Customer (
  id int NOT NULL AUTO_INCREMENT,
  ssn char(11) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  phone char(12) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ssn (ssn)
)
```
2. Create Users table and populate it with user and password
```
CREATE TABLE Users (
  name varchar(50) NOT NULL,
  pass varchar(50) NOT NULL,
  PRIMARY KEY (name)
)
```
3. Additional tables you might need for other parts of this example
```
CREATE TABLE `CreditCard` (
  `card_number` int NOT NULL AUTO_INCREMENT,
  `type` enum('Visa','Master Card','American Express','Discover') NOT NULL,
  `limit` double DEFAULT NULL,
  `balance` double(10,2) DEFAULT '0.00',
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`card_number`)
);
CREATE TABLE `Vendor` (
  `vid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vid`)
);
CREATE TABLE `Ownership` (
  `owner_id` int NOT NULL,
  `card_number` int NOT NULL,
  `current` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`owner_id`,`card_number`),
  KEY `card_number` (`card_number`),
  CONSTRAINT `Ownership_ibfk_1` FOREIGN KEY (`card_number`) REFERENCES `CreditCard` (`card_number`)
);
CREATE TABLE `Transaction` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `cid` int NOT NULL,
  `card_number` int NOT NULL,
  `vid` int NOT NULL,
  `amount` double(10,2) NOT NULL,
  `tdate` date NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `cid` (`cid`),
  KEY `card_number` (`card_number`),
  KEY `vid` (`vid`),
  CONSTRAINT `Transaction_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `Customer` (`id`),
  CONSTRAINT `Transaction_ibfk_2` FOREIGN KEY (`card_number`) REFERENCES `CreditCard` (`card_number`),
  CONSTRAINT `Transaction_ibfk_3` FOREIGN KEY (`vid`) REFERENCES `Vendor` (`vid`)
);
CREATE TABLE `Payment` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `card_number` int NOT NULL,
  `amount` double(10,2) NOT NULL,
  `pdate` date NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `card_number` (`card_number`),
  CONSTRAINT `Payment_ibfk_1` FOREIGN KEY (`card_number`) REFERENCES `CreditCard` (`card_number`)
);
```
### To Run this example locally on your computer
1. In IntelliJ, for example, set a RUN configuration by clicking "Edit Configurations" under the Run menu.
2. Create a Maven build and put the following in the Command Line text box:
```
clean compile war:war tomcat7:run
``` 
3. Run the project with the above configuration.
