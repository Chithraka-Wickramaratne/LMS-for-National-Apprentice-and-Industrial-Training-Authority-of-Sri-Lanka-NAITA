# LMS-for-National-Apprentice-and-Industrial-Training-Authority-of-Sri-Lanka-NAITA

A web-based Learning Management System (LMS) for managing users and course materials across various disciplines such as Automobile Engineering, Hotel Sector, Electrical Technician, ICT, and Software Engineering. This system allows admins to manage users, lecturers to upload materials, and students to access and download their course content.

Features
Admin Role:

Add, edit, and delete users (Lecturers & Students).
Assign users to courses and send email notifications with login credentials.
Manage courses and materials for all users.
Lecturer Role:

Upload, update, and delete lecture materials for their respective courses.
Manage students enrolled in their courses.
Student Role:

Login and access their specific course page.
Download and view course materials uploaded by lecturers.
Tech Stack
Frontend: React.js, Bootstrap
Backend: Spring Boot
Database: Google Firestore
Authentication: Role-based login system with email and password.
Installation
To run the project locally, follow these steps:

Clone the repository:

bash
Copy code
git clone (https://github.com/Chithraka-Wickramaratne/LMS-for-National-Apprentice-and-Industrial-Training-Authority-of-Sri-Lanka-NAITA)
Navigate to the frontend directory and install dependencies:

bash
Copy code
cd frontend
npm install
npm start
Set up the backend with Spring Boot by running the application in your IDE or via the terminal:

bash
Copy code
mvn spring-boot:run
Configure Firestore in the backend to connect with the database.

