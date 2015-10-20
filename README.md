# Asso
## An Android application to manage association

### Classes

####Model
* Association
* News
* Member
  * StaffMember
* User (singleton)
* Role (enum)

####Activity

*HomeActivity
*LoginActivity
*SignUpActivity

####Database

*AssoDatabase

**Association**:

* has a name, an address, a description, a list of **News**, a list of **Member** and a list of **StaffMember**
* must have at least two **StaffMember** (President, Treasurer)

**User** is the singleton needed to login

**StaffMember** has a role which is one item of **Role**

**HomeActivity is the main activity. This is where the user manages the association, sees members, news etc

**LoginActivity is the sign in page. It has to have a connected user

**SignUpActivity is the account creation page. To create an account, you go there

**AssoDatabase is the class to use to manage the mongo database (which is done in Pharo)

##Example of use case

* A **User** opens the app
* Sign in on the first screen (optionnaly create an account)
* He can choose between display a list of association's members and display news

##Log

###15/10/2015

	*Created the model 
	*Created the basics activities (Home, Login and sign up)
	*Created the User singleton to manage sessions
	*Created the sign up page