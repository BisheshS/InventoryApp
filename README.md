# InventoryApp


The goal is to design and create the structure of an Inventory App which would allow a store to keep track of its inventory of products. The app will need to store information about price, quantity available, supplier, and a picture of the product. It will also need to allow the user to track sales and shipments and make it easy for the user to order more from the listed supplier.


What will I Iearn?
This project is about combining various ideas and skills we’ve been practicing throughout the course. They include:

`.Storing information in a SQLite database
2.Integrating Android’s file storage systems into that database
3.Presenting information from files and SQLite databases to users
4.Updating information based on user input.
5.Creating intents to other apps using stored information.





ListView Population

The listView populates with the current products stored in the table.

Add product button

The Add product button prompts the user for information about the product and a picture, each of which are then properly stored in the table.

Input validation

User input is validated. In particular, empty product information is not accepted.

Sale button

The sale button on each list item properly reduces the quantity available by one, unless that would result in a negative quantity.

Detail View intent

Clicking on the rest of each list item sends the user to the detail screen for the correct product.

Modify quantity buttons

The modify quantity buttons in the detail view properly increase and decrease the quantity available for the correct product.

The student may also add input for how much to increase or decrease the quantity by.

Order Button

The ‘order more’ button sends an intent to either a phone app or an email app to contact the supplier using the information stored in the database.

Delete button

The delete button prompts the user for confirmation and, if confirmed, deletes the product record entirely and sends the user back to the main activity.
