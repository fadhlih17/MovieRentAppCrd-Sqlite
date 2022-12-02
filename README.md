# MovieRentAppCrd-Sqlite
Movie Rent App OOP with Sqlite (Simple project with java basic database)
• The application consists of 4 menus:
1. Add New Movie
2. View Movie (Sort by Title ascending)
3. Rent Movie
4. Exit

• If the user chooses menu 1 (“Add New Movie”), the application will:
o Ask user to input Movie’s Title. Validate that the Title’s length must be between 3 
and 20 characters 
o Ask user to input Movie’s Genre. Validate that the Movie’s genre must be filled with 
either “Horror” , “Comedy”, or “Action” (case sensitive)
o Ask user to input Movie Rating. Validate that the Movie’s Rating must be between 1 
and 10
o Generate the Movie’s ID based on the following format

• If the user chooses menu 2 (“View Movie Sort by Title Ascending”):
o If there is no Movie in the list, show a warning message “No Movie Found”
o Otherwise, show all Movies in the list, sorted by title ascending

• If the user chooses menu 3 (“Rent Movie”) :
o If there is no Movie in the list, show a warning message “No Movie Found”
o Ask user to input Movie’s index to rent. Validate that the index must be between 1 
and total of Movie’s and index must be numeric
o Ask user to input Money for paying rent. Validate that the money must be more than 
equals the Price then show success message “Pay Rent Successful with [change] 
Change”
o Delete the movie from the list afterwards

• If user chooses menu 4 (“Exit”), the application will exi
