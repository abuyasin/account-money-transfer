# account-money-transfer
Account to Account Money transfer or request REST API Service. JDK1.8, Spring MVC, apache tomcat 8.5.*

Account Money Transfer REST API Service
1.	Overview
It is a web enable service used to create, update, list or view of Accounts and account to account money transfer and request service. By integrating this API service any system can transfer or request money from one account to another account and also can accept or reject any money request. This API services receive and provides data in JSON format. Generally API services are build to implement two or multi tier application system.
2.	Solution Design [A general scenario of API use]
2.1.	Tier One: The users, who will use the system from terminal machine.
2.2.	Tier Two: The frontend server, it will integrate the API service to communicate, transfer and receive data from the database, frontend server don’t have direct access to database.
2.3.	Tier Three:  The REST API service, it is the middleware and it can serve data to multiple Systems like Web Server, Desktop System, mobile applications and others etc. I’ve implemented this REST API web service using J2EE, Spring MVC with maven repository and deployed in apache tomcat server.
2.4.	Tier Four: Database can’t be directly access by any system; it can be only accessed through/via API service. I’ve used Oracle database.
Tier 1	Tier 2	Tier 3	Tier 4
 
Client Terminal or User Machine.	 
Front-End Server could be an web application.	 
Middleware REST API service. It will serve REST API service.	 
Database

3.	Development Tools & Technologies Used
3.1.	IDE/Tools: Netbeans 8.2, SQLDeveloper
3.2.	Programming Languages: Java, J2EE with Spring MVC framework.
3.3.	Libraries: Spring MVC, Spring Core, Spring Security, Mybatis and some others through maven.
3.4.	Repository: Maven, built in with Netbeans.
3.5.	Database: Oracle 11g
4.	Source code and Database Structure
4.1.	Source code structure: 
4.1.1.	Have spitted the DTO [acc-transfer-dto], DAO & DB query [acc-transfer-core] and Web Service [REST] in separate module [sub-project] from the parent project. Here acc-transfer-dto & acc-transfer-core together makes the Model and acc-transfer-rest is the Controller and View.
4.1.2.	DTO [acc-transfer-dto] and CORE [acc-transfer-core] are integrated into REST as library [jar] through maven.  
4.1.3.	Uploaded the source code into github.
4.2.	Database Structure: 
4.2.1.	Very simple structure, have uploaded the dump SQL into github
4.3.	GIT URL: source code and database structure can be found here: https://github.com/abuyasin/account-money-transfer 
5.	Deployment: To build the project do the following steps:
5.1.	Clean and Build the DTO [acc-transfer-dto]
5.2.	Change the database connection at acc-transfer-core-applicationContext.xml of acc-transfer-core project. Clean and Build the CORE [acc-transfer-core].
5.3.	Clean and Build the REST [acc-transfer-rest].
5.4.	Get the *.war file from the folder named “target” of REST [acc-transfer-rest] project and put it into the apache tomcat. The REST API Web service will be available through web url.
6.	Limitations
6.1.	Messages: Validation and error messages need to be simple. Have to customize the messages.
6.2.	More test/conditions: Have handled few checking while creating account and transferring money from account to account [ex: can’t transfer money if transfer request amount is greater than source account balance]. Have to check more conditions.
6.3.	Unit Testing: Have added JUnit library through maven but couldn’t able to try or test.
6.4.	Audit: Couldn’t able to add audit trail or necessary columns into database [ex: user id, request time, updates time etc.].
6.5.	Source code Comments: Have commented only in the REST controller classes, Have to do comments/documentation on DTO, DAO classes too.
6.6.	Documentation: This documentation has to be modified for easy understanding for everyone.

7.	How to use this API?
Any programmer can integrate the following API service URL in any system using any languages.
7.1.	Create Account
URL
http://localhost:8084/acc-transfer-rest/account/create
Request Method
POST
Content-Type
1	Content-Type	application/json	Set in header	Yes

Parameters 
SL No
	Parameter
	Value
	Remarks
	Required
1	username	Abc	Basic authentication, set in basic auth.	Yes
2	password	123456		Yes
3	accountNo	18506434	Notnull, Minimum 8, maximum 20 characters long	Yes
4	fullName	Abu Yasin	Notnull, maximum 100 characters long	Yes
5	Balance	1000	Not null	Yes

Sample request /response
Success Response (JSON)
{
  "code": 1,
  "msg": "success",
  "accountNo": "01723015039",
  "fullName": "Abu Sadat Mohammed Yasin",
  "balance": 1000
}

Error Response (JSON)
{
  "code": -1,
  "msg": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'accountData' on field 'fullName': rejected value [Abu Yasin]; codes [Pattern.accountData.fullName,Pattern.fullName,Pattern.java.lang.String,Pattern]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [accountData.fullName,fullName]; arguments []; default message [fullName],[Ljavax.validation.constraints.Pattern$Flag;@b482c9,org.springframework.validation.beanvalidation.SpringValidatorAdapter$ResolvableAttribute@f3c5e3]; default message [fullName Must be alpha numeric]",
  "accountNo": "18506434",
  "fullName": "Abu Yasin",
  "balance": 1000
}

7.2.	List of Accounts
URL
http://localhost:8084/acc-transfer-rest/account/list
Request Method
GET
Parameters 
SL No
	Parameter
	Value
	Remarks
	Required
1	username	Abc	Basic authentication, set in basic auth.	Yes
2	password	123456		Yes
3	accountNo	18506434	Optional, if assigned it will return an specific single row or null or empty data	No

Sample request /response
Success Response (JSON)
[
  {
    "code": 0,
    "msg": null,
    "accountNo": "01723015039",
    "fullName": "Abu Sadat Mohammed Yasin",
    "balance": 1000
  },
  {
    "code": 0,
    "msg": null,
    "accountNo": "18506434",
    "fullName": "Abu Yasin",
    "balance": 300
  }
]

Error Response (JSON)
{
  "code": -1,
  "msg": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'accountData' on field 'fullName': rejected value [Abu Yasin]; codes [Pattern.accountData.fullName,Pattern.fullName,Pattern.java.lang.String,Pattern]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [accountData.fullName,fullName]; arguments []; default message [fullName],[Ljavax.validation.constraints.Pattern$Flag;@b482c9,org.springframework.validation.beanvalidation.SpringValidatorAdapter$ResolvableAttribute@f3c5e3]; default message [fullName Must be alpha numeric]",
  "accountNo": "18506434",
  "fullName": "Abu Yasin",
  "balance": 1000
}

7.3.	Update a Account
URL
http://localhost:8084/acc-transfer-rest/account/update
Request Method
POST
Content-Type
1	Content-Type	application/json	Set in header	Yes

Parameters 
SL No
	Parameter
	Value
	Remarks
	Required
1	username	Abc	Basic authentication, set in basic auth.	Yes
2	password	123456		Yes
3	accountNo	18506434	Notnull, Minimum 8, maximum 20 characters long	Yes
4	fullName	Abu Yasin	Notnull, maximum 100 characters long	Yes
5	balance	1000	Not null	Yes

Sample request /response
Success Response (JSON)
{
  "code": 1,
  "msg": "success",
  "accountNo": "01723015039",
  "fullName": "Abu Sadat Mohammed Yasin",
  "balance": 1000
}

Error Response (JSON)
{
  "code": -1,
  "msg": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'accountData' on field 'fullName': rejected value [Abu Yasin]; codes [Pattern.accountData.fullName,Pattern.fullName,Pattern.java.lang.String,Pattern]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [accountData.fullName,fullName]; arguments []; default message [fullName],[Ljavax.validation.constraints.Pattern$Flag;@b482c9,org.springframework.validation.beanvalidation.SpringValidatorAdapter$ResolvableAttribute@f3c5e3]; default message [fullName Must be alpha numeric]",
  "accountNo": "18506434",
  "fullName": "Abu Yasin",
  "balance": 1000
}

7.4.	Create a Transfer from one account to another account [checks for sufficient balance]
URL
http://localhost:8084/acc-transfer-rest/transfer/create
Request Method
POST
Content-Type
1	Content-Type	application/json	Set in header	Yes

Parameters 
SL No
	Parameter
	Value
	Remarks
	Required
1	username	Abc	Basic authentication, set in basic auth.	Yes
2	password	123456		Yes
3	srcAccountNo	18506434	Notnull, Minimum 8, maximum 20 characters long	Yes
4	desAccountNo	Abu Yasin	Notnull, Minimum 8, maximum 20 characters long	Yes
5	amount	1000	Not null	Yes
6	type	t	t= Transfer or r = Request, notnull	
7	status	1	0=while request, 1=while accept	No

Sample request /response
Success Response (JSON)
{
  "code": 1,
  "msg": "success",
  "transactionId": "2017-10-08-21-26-14-864:77524",
  "srcAccountNo": "2856434",
  "desAccountNo": "18506434",
  "amount": 250,
  "type": "t",
  "status": 1
}

Error Response (JSON)
{
  "code": -1,
  "msg": "Insufficiant balance into source Account",
  "transactionId": null,
  "srcAccountNo": "2856434",
  "desAccountNo": "18506434",
  "amount": 2500,
  "type": "t",
  "status": 0
}

7.5.	Update a Transfer[Need to accept or reject if anyone made a request for money]
URL
http://localhost:8084/acc-transfer-rest/transfer/update
Request Method
POST
Content-Type
1	Content-Type	application/json	Set in header	Yes

Parameters 
SL No
	Parameter
	Value
	Remarks
	Required
1	username	Abc	Basic authentication, set in basic auth.	Yes
2	password	123456		Yes
3	transactionId	2017-10-08-21-30-47-569:20217	The id of the request transfer	Yes
4	type	R	t= Transfer or r = Request, notnull, only request is allow to updated in this update service.	Yes
5	status	1	1=accept, 2=reject	Yes

Sample request /response
Success Response (JSON)
{
  "code": 1,
  "msg": "success",
  "transactionId": "2017-10-08-21-30-47-569:20217",
  "srcAccountNo": "2856434",
  "desAccountNo": "18506434",
  "amount": 2500,
  "type": "r",
  "status": 1
}

Error Response (JSON)
{
  "code": -1,
  "msg": "Insufficiant balance into source Account, Can't be accept.",
  "transactionId": "2017-10-08-21-30-47-569:20217",
  "srcAccountNo": "2856434",
  "desAccountNo": "18506434",
  "amount": 2500,
  "type": "r",
  "status": 2
}

8.	Conclusion:
REST API with JSON formatted data is light weight, faster and popular service in current Software Development World.
9.	Contact
Author: Abu Sadat Mohammed Yasin
Email & Skype: abusadatyasin@gmail.com
