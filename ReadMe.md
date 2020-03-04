# Charity Donation App


Charity Donation app is an android application written in 'KOTLIN' that lists down charities and let people donate for the selected charity using their credit card via OMISE Payment Gateway(https://www.omise.co/) , it is designed using android's architectural components such as :

  - ViewModel
  - Live Data
# Screenshots

<img src="https://github.com/smtrz/charity-donation-app/blob/master/screen_1.png" alt="drawing" width="350"/>  |  <img src="https://github.com/smtrz/charity-donation-app/blob/master/screen_2.png" alt="drawing" width="350"/>

# Note
 1) For Listing charity following 'GET' end-point is used :
    - https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0/charities
 
 2) For generating Payment token following OMISE Token end-point is used.
    - https://vault.omise.co/tokens
 3) For charging the customer using the token generated the following 'POST' end-point is used :
    - https://api.omise.co/charges
# Testing cards for Donation :
Testing cards can be obtained from the below mentioned link :

   - https://www.omise.co/api-testing



# Features

  - Easily extendible
  - Clean UI and code
  - SSL public key pinning
  - Proguard Ready
  - NDK support for hiding senstive data and keys from reverse engineering
  - Ready test cases for testing successful and failed transactions.


### Tech

This app uses a number of open source projects Libraries and frameworks:

* [Kotlin] - written in kotlin
* [SOLID] - Use of Solid Design principles
* [Dagger2] - Dependency injection using Dagger2
* [Repository pattern] - Use of repository pattern for data storage and retrievel
* [View Model]

### Installation

The app currently has only one branch(master),just check out that branch to get started.


License
----
Designed and developed by :

Tahir Raza<br/>
smtrz@yahoo.com<br/>
Skype: smtrz110<br/>
Profile: https://www.linkedin.com/in/tahiraza/<br/>
More about me : http://highbryds.com/tahir-raza

