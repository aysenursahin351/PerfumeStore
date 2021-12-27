# Online-PerfumeStore
Scenario
The customer first registers and then logs in. They can search for the item and add it to
the cart if they want to buy it. They can edit the cart: They can add or remove items from the
cart. If they want to order the items, they are directed to the payment screen. They can make
the payment by credit card or debit card. If it is appropriate, the customer gives the item to
the cargo company and a refund is made to the customer's bank account. Admin can delete the
complained items. In addition, the admin also manages the stock.

Code Structure
Classes are designed in accordance with the object oriented programming rules as
suggested. This project has 29 classes in total. 10 classes that i use to show screens are
(LoginScreen, UserDetailsScreen, ComplainScreen, PerfumeListScreen, ReceiptScreen,
AlphabeticalScreen, AdminScreen, BasketScreen, AddItemScreen, SignUpScreen) all
extends from the Screen class which is JPanel class. CheckoutPopup, PerfumeThumbnail
and AdminThumbnail classes are derived from JDialog class.All screen classes connected
to ShopController Class to acquire data from PerfumeShop class. Multiple object classes
are created to be used in a Shop class which is main object: PerfumeShop. Class
structures and their methods are explained below.
The connection between user and program is important of the project this requires a
effective graphical user interface to do so Swing library and Window Builder in Eclipse IDE
was used in the project.
