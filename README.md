# Asso
## An Android application to manage association

### Classes

* Association
* News
* Member
  * StaffMember
* User (singleton)
* Role (enum)

**Association**:

* contains a list of **News** and a list of **Member**
* must have at least two **StaffMember** (President, Treasurer)

**User** is the singleton needed to login

**StaffMember** has a role which is one item of **Role**

##Example of use case

* A **User** open the app
* Loggin on the first screen (optionnaly create an account)
* He can choose between display a list of association's members and display news

##Log

###15/10/2015

	*Created the model 
	*Created the basics activities (Home, Login and sign up)
	*Created the User singleton to manage sessions