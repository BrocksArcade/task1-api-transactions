# task1-api-transactions
# Project-Overview :
This project aims to showcase ability to integrate RESTFul API's with Android apps . The API used in project contains logic for Authentication on Server Side and usage of same on client (application) side.
The project fetches the Transaction details from API and displays to User on Screen in list format , with fields as ID, Date, Category , Amount and Description. The app has logout button on top right corner which removes the user information from persistant storage.

 # Setup-Instructions
1.Turn on WIFI Or Cellular Data
2.Open the application
3.Unlock the app using Biometric fingerprint/Pattern/Pin of your phone 
4.Depending on internet availability, try to login using valid username and password . If both are correct you should get redirected to dashboard otherwise a toast message will pop up as "invalid credentials".
5.After login , wait for few seconds to let app fetch data from API , speed of fetching depends on internet speed of your phone. 
6.After executing the app , user can choose to press Logout button on top right corner or exit the app by pressing back button.
# APK Build Instructions 
Apks are stored in {projectfolder}\task1-api-transactions\app\release
You will require Key store file to regenerate a signed apk which can be provided if asked through email ONLY.

# Bonus Features
1.Dark-Mode - clicking on Cresent Moon Icon (near search bar) will toggle dark mode , pressing it again will toggle light mode
2.Search - Click on search bar and start typing any category or transaction's description and items will be filtered, click X or close button to Reset the list
3.Offline Mode - If app was able to fetch list at least once through API , app can persist that list even if No internet connection was found . Even users can search through that list
4.Extra-Bonus feature - Swipe from topdown to refresh the list.
