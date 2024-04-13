Project Overview: Kotlin Firebase App with Client and Admin Modules

This Kotlin application is designed to manage user data using Firebase as the backend. It consists of two distinct modules: the Admin module and the Client module.

Admin Module:

The Admin module is responsible for handling administrative tasks related to user data management. It provides functionalities for creating, updating, and deleting user records within the Firebase database.

Features of the Admin Module:

Authentication: Administrators are authenticated using Firebase Authentication to ensure secure access to administrative functionalities.

User Interface: The Admin module includes an intuitive user interface (UI) allowing administrators to interact with the application seamlessly.

CRUD Operations:

Create: Administrators can create new user records by entering relevant information such as name, email, and other details into the application's UI. Upon submission, the data is stored in the Firebase database.
Update: Existing user records can be modified by administrators. They can update information such as name, email, or any other relevant details associated with a user.
Delete: Administrators have the ability to delete user records from the Firebase database when necessary.
Client Module:

The Client module caters to users who have access to read data but do not possess administrative privileges. It allows clients to retrieve and view user data stored in the Firebase database.

Features of the Client Module:

Authentication: Clients are also authenticated using Firebase Authentication to ensure secure access to the application's data.

User Interface: The Client module provides a user-friendly interface allowing clients to view user data retrieved from the Firebase database.

Read Operations:

View Data: Clients can access and view user data presented within the application's UI. This data is fetched from the Firebase database in real-time, ensuring that clients have access to the most up-to-date information.
Firebase Integration:

The application leverages Firebase as its backend infrastructure for storing, retrieving, and managing user data. Firebase Realtime Database or Firestore is utilized to store user records securely in a structured format.
