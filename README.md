# 🌐 BlogNation — Personal Blogging Platform

🔗 **Live Site:** "https://blognation-frontend.onrender.com"

---

## 📌 Overview

**BlogNation** is a fully functional multi-user blogging platform built with **Spring Boot** (backend) and **HTML/CSS/JS** (frontend). Users can create, edit, delete, like, comment on, and follow blog posts.

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot, Spring Data JPA
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL
- **Hosting:**  
  - Backend: [Render](https://render.com/)  
  - Frontend: (Optional: GitHub Pages, Netlify, or Vercel)
  - Database: postgre on render 
---

## 🚀 Features Implemented

### 👤 Authentication
- User **Signup/Login**
- Email-based registration
- Username stored for display

### 📝 Blog Management
- Create, edit, and delete posts
- Each post has a title, content, author, image, and timestamp
- Posts are linked to users via `userId`

### 📖 Feed
- Public blog feed with all posts
- **Reverse chronological order** (latest first)
- **"Following" tab** to show posts only from followed authors
- **Search** by title or author (case-insensitive)

### ❤️ Interaction
- Like/unlike posts
- Comment on posts
- See comment count and like count per post
- Toggle comment section inline

### ➕ Follow System
- Follow/unfollow any author
- Followers list on dashboard
- Shows follow state (Follow/Unfollow) dynamically

### 📊 Dashboard
- View all your posts with like/comment counts
- View and hide followers list
- Clean UI with comment viewing

### 🖼️ Frontend
- Clean responsive UI using vanilla HTML/CSS
- Profile dropdown for quick navigation:
  - Create Post
  - Edit Post
  - Delete Post
  - Dashboard
  - Logout

### 🔐 Backend Security
- Posts are only editable/deletable by their creator
- Old orphan posts ignored or cleaned up

---


## 📦 How to Run Locally

### 1. Clone the Repo
```bash
git clone https://github.com/YOUR_USERNAME/blognation.git
cd blognation
```

### 2. Backend Setup (Spring Boot)
- Create a MySQL database named `blogapi`
- Update `src/main/resources/application.properties` with:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/blogapi?useSSL=false
  spring.datasource.username=your_mysql_user
  spring.datasource.password=your_mysql_password
  spring.jpa.hibernate.ddl-auto=update
  ```
- Run the project using your IDE or:
  ```bash
  ./mvnw spring-boot:run
  ```

### 3. Frontend Setup
- Open `feed.html`, `login.html`, etc. using Live Server or host on Netlify/GitHub Pages
- Make sure API calls point to your backend URL (e.g. Render or localhost)

---

## 🌍 Deployment Info

- **Backend** hosted on [Render](https://render.com)
- **Frontend** can be hosted on:
  - GitHub Pages (static)
  - Netlify (recommended)
  - Vercel (if preferred)

---

## 👨‍💻 Author

**Aryan Singh**  
A 20-year-old software engineering enthusiast who loves building cool things!  
Passionate about football 🥅, basketball 🏀, and creating awesome tech.

---

## 📧 Contact

Feel free to reach out for feedback, collaboration, or questions!  
📩 Email: *[your-email@example.com]*  
🔗 GitHub: [@AryanSingh2029](https://github.com/AryanSingh2029)

---

## 🏁 Final Words

> First project — many iterations, much learning, and a LOT of fun.  
> Hope you enjoy using it as much as I enjoyed building it. 😊
