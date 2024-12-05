# 🚀 National Apprentice and Industrial Training Authority LMS

A **web-based Learning Management System (LMS)** for managing users and course materials across disciplines such as **Automobile Engineering**, **Hotel Sector**, **Electrical Technician**, **ICT**, and **Software Engineering**. This system empowers **Admins**, **Lecturers**, and **Students** with efficient tools for user management, course assignments, and material access.


---

## 🌟 Features

### 👑 Admin Features
- Add, edit, and delete **Lecturers** and **Students**.
- Assign users to specific courses.
- Send **automated email notifications** with login credentials.
- Manage courses and user enrollments.

### 👨‍🏫 Lecturer Features
- Upload, update, and delete **lecture materials** (PDFs, videos, etc.).
- Manage and view students in their assigned courses.

### 📚 Student Features
- Login and access **course materials** based on their course.
- Download materials for **self-paced learning**.

---

## 🛠️ Tech Stack

- **Frontend:** React.js, Bootstrap  
- **Backend:** Spring Boot  
- **Database:** Google Firestore  
- **Authentication:** Role-based login system (Admin, Lecturer, Student)

---

## 📦 Installation

To run the project locally, follow these steps:

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Chithraka-Wickramaratne/LMS-for-National-Apprentice-and-Industrial-Training-Authority-of-Sri-Lanka-NAITA

###2️⃣ Install Frontend Dependencies
bash
Copy code
cd frontend
npm install
npm start

###3️⃣ Run the Backend
bash
Copy code
mvn spring-boot:run

###4️⃣ Configure Firestore
Set up Google Firestore in the backend for data storage.

###💡 Key Highlights
Role-Based Access Control: Ensures only authorized users access specific features.
Seamless Course Management: Enables admins and lecturers to manage enrollments and materials efficiently.
Real-Time Updates: Changes made by the admin or lecturer are instantly reflected to students.
