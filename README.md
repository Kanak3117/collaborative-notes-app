# 📝 CollabNotes — Real-time Collaborative Notes App

A production-ready real-time collaborative notes application built with Java and Spring Boot, similar to Google Docs.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-purple)
![Deploy](https://img.shields.io/badge/Deploy-Render-brightgreen)

## 🌐 Live Demo
**[👉 Click here to view live app](https://collaborative-notes-app-2.onrender.com/index.html)**

> Note: First load may take 30-60 seconds (free hosting)

## ✨ Features

- 🔐 **JWT Authentication** — Secure Register/Login system
- 📝 **Notes CRUD** — Create, Read, Update, Delete notes
- ⚡ **Real-time Collaboration** — Multiple users edit simultaneously
- 👥 **Online Users** — See who is currently editing
- ✏️ **Typing Indicator** — See when others are typing
- 🔴 **Live Activity Log** — Track all changes in real-time
- ✅ **Input Validation** — Clean error messages
- 🛡️ **Global Exception Handling** — Proper error responses
- 🎨 **Professional Dark UI** — Modern responsive design

## 🛠️ Tech Stack

### Backend
| Technology | Purpose |
|---|---|
| Java 17 | Programming Language |
| Spring Boot 4.0 | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT (JSON Web Token) | Secure token-based auth |
| Spring Data JPA | Database ORM |
| WebSocket + STOMP | Real-time communication |
| Hibernate | Database mapping |

### Database
| Technology | Purpose |
|---|---|
| MySQL 8.0 | Production database |
| H2 | Development/testing |

### Frontend
| Technology | Purpose |
|---|---|
| HTML5 | Structure |
| CSS3 | Styling (Dark theme) |
| JavaScript | Client-side logic |
| SockJS + STOMP.js | WebSocket client |

### DevOps
| Technology | Purpose |
|---|---|
| Docker | Containerization |
| Render | Cloud deployment |
| Clever Cloud | MySQL hosting |
| GitHub | Version control |

## 🏗️ Project Structure
src/
├── main/
│ ├── java/com/example/demo/
│ │ ├── controller/ # REST API endpoints
│ │ │ ├── AuthController # Register & Login
│ │ │ ├── NoteController # CRUD operations
│ │ │ └── NoteWebSocketController # Real-time
│ │ ├── model/ # Database entities
│ │ │ ├── Note # Note entity
│ │ │ ├── User # User entity
│ │ │ └── NoteMessage # WebSocket message
│ │ ├── repository/ # Data access layer
│ │ ├── security/ # JWT implementation
│ │ │ ├── JwtUtil # Token generation
│ │ │ └── JwtFilter # Request filter
│ │ ├── exception/ # Error handling
│ │ │ └── GlobalExceptionHandler
│ │ ├── config/ # Configuration
│ │ │ └── WebSocketConfig
│ │ └── SecurityConfig # Security rules
│ └── resources/
│ ├── static/index.html # Frontend UI
│ └── application.properties

## 🔌 API Endpoints

### Authentication
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login & get JWT token |

### Notes
| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/notes` | Get all notes | ✅ Required |
| POST | `/api/notes` | Create new note | ✅ Required |
| GET | `/api/notes/{id}` | Get note by ID | ✅ Required |
| PUT | `/api/notes/{id}` | Update note | ✅ Required |
| DELETE | `/api/notes/{id}` | Delete note | ✅ Required |

### WebSocket
| Endpoint | Description |
|---|---|
| `/ws` | WebSocket connection |
| `/app/note.edit` | Send edit event |
| `/app/note.join` | Join note session |
| `/app/note.leave` | Leave note session |
| `/topic/notes` | Subscribe to updates |

## 🚀 How to Run Locally

### Prerequisites
- Java 17+
- MySQL 8.0+
- Gradle

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/Kanak3117/collaborative-notes-app.git
cd collaborative-notes-app
```

2. **Setup MySQL database**
```sql
CREATE DATABASE notesdb;
```

3. **Configure application.properties**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/notesdb
spring.datasource.username=root
spring.datasource.password=yourpassword
```

4. **Run the application**
```bash
./gradlew bootRun
```

## 🧪 Testing the API

### Register
```bash
POST /api/auth/register
{
    "username": "kanak",
    "email": "kanak@gmail.com",
    "password": "pass123"
}
```

### Login
```bash
POST /api/auth/login
{
    "username": "kanak",
    "password": "pass123"
}
# Returns JWT token
```

### Create Note (with token)
```bash
POST /api/notes
Authorization: Bearer {your-jwt-token}
{
    "title": "My Note",
    "content": "Hello World!",
    "author": "kanak"
}
```

## 🔒 Security Features

- JWT token-based authentication
- BCrypt password hashing
- Protected API endpoints
- CORS configuration
- Input validation
- Global exception handling

## 👩‍💻 Author

**Kanak Varshney**

[![GitHub](https://img.shields.io/badge/GitHub-Kanak3117-black)](https://github.com/Kanak3117)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue)](https://linkedin.com/in/your-profile)

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
