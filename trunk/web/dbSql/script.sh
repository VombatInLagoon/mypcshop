#!/bin/bash
#shell script  to query mysql
db= #db of the table
username=root # you need to provide the username here
pwd=sesame #here you need to enter password
host=localhost #here hostame

mysql -u $username -p $pwd -D $db < setup.sql

# a user name "root" with password "sesame" must exist in the database system!
