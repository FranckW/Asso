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
* MemberFilter
* MemberListViewAdapter
* Members (singleton)

####Activity

*HomeActivity
*LoginActivity
*SignUpActivity
*MemberActivity
*MembersListActivity

####Utils

* Utility
* ActivitiesHistory

**Association**:

* has a name, an address, a description, a list of **News**, a list of **Member** and a list of **StaffMember**
* must have at least two **StaffMember** (President, Treasurer)

**User** is the singleton needed to login

**Member** implements Parcelable to be able to transfer a member object between activities (such as a list of members -> selected member's profil)

**Members** is the singleton used to manipulate all the members without doing an http request each time to get them

**StaffMember** has a role which is one item of **Role**

**HomeActivity** is the main activity. This is where the user manages the association, sees members, news etc

**LoginActivity** is the sign in page. It has to have a connected user

**SignUpActivity** is the account creation page. To create an account, you go there

**MemberActivity** is the page to see a member's profil. You can email or call him from this activity

**MembersListActivity** is the list of all the members which can be filtered

**ActivitiesHistory** is a singleton used to keep a trace of all the running activities. It is used to flush the stack when user logs out. It prevents user from going back in running activities when he is no longer logged in