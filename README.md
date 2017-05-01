# PCShop
Simple JavaEE shopping application. In order to deploy you have to setup Apache tomcat or tomee on your machine.
Add the following attribute into your `server.xml` located under tomcat/conf and also make sure you have mysql driver jar 
file inside tomcat's `lib` folder:

      <Realm className="org.apache.catalina.realm.JDBCRealm" debug ="99"
             driverName="com.mysql.jdbc.Driver"
	     connectionURL="jdbc:mysql://localhost/pcshop?user=root&amp;password=sesame"
	     userTable="USERS" userNameCol="USER_NAME" userCredCol="USER_PASS"
	     userRoleTable="USER_ROLES" roleNameCol="ROLE_NAME"/> 
       
Before deploying the app run the `setup.sh` script under `webapp/dbSql` folder in order to setup the database.
Note that a `root` user with password `sesame` is to be available in MySQL.
