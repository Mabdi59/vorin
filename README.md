# VORIN - Tournament Management System

VORIN is a modern, full-stack tournament management platform built with React, Spring Boot, and PostgreSQL. Create, schedule, and manage tournaments with live results, teams, venues, and bracket automation.

## Features

- **User Authentication**: JWT-based authentication with secure login/signup
- **Tournament Management**: Full CRUD operations for tournaments
- **Division Support**: Organize tournaments into divisions with different bracket types
- **Team Management**: Register and manage teams within divisions
- **Venue Management**: Track and manage tournament venues
- **Scheduling Engine**: Automatic schedule generation for:
  - Round-robin tournaments
  - Single-elimination brackets
  - Double-elimination brackets
- **Live Score Updates**: Real-time score updates using WebSocket
- **Match Management**: Track match results and automatically update team standings

## Technology Stack

### Backend
- **Java 17**: LTS version of Java (compatible with Java 21)
- **Spring Boot 3.2.0**: Modern Spring framework
- **Spring Security**: JWT authentication
- **Spring Data JPA**: Database persistence
- **Spring WebSocket**: Real-time updates
- **PostgreSQL**: Relational database
- **Maven**: Build and dependency management

### Frontend
- **React 18**: Modern UI library
- **TypeScript**: Type-safe JavaScript
- **Vite**: Fast build tool
- **React Router**: Client-side routing
- **Axios**: HTTP client
- **STOMP/SockJS**: WebSocket client

## Prerequisites

- Java 17 or higher (Java 21 recommended for production)
- Node.js 18 or higher
- Docker and Docker Compose (for PostgreSQL)
- Maven 3.6 or higher

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Mabdi59/vorin.git
cd vorin
```

### 2. Start PostgreSQL Database

```bash
docker-compose up -d
```

This will start a PostgreSQL database on port 5432.

### 3. Start the Backend

```bash
cd backend
./mvnw spring-boot:run
```

The backend will start on `http://localhost:8080`.

### 4. Start the Frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:5173`.

### 5. Access the Application

Open your browser and navigate to `http://localhost:5173`.

**Default Test Account** (create via signup page):
- Username: admin
- Password: admin123

## API Endpoints

### Authentication
- `POST /api/auth/signup` - Register new user
- `POST /api/auth/login` - Login user

### Tournaments
- `GET /api/tournaments` - Get all tournaments
- `GET /api/tournaments/{id}` - Get tournament by ID
- `POST /api/tournaments` - Create tournament
- `PUT /api/tournaments/{id}` - Update tournament
- `DELETE /api/tournaments/{id}` - Delete tournament

### Divisions
- `GET /api/divisions` - Get all divisions
- `GET /api/tournaments/{tournamentId}/divisions` - Get divisions by tournament
- `POST /api/tournaments/{tournamentId}/divisions` - Create division
- `PUT /api/divisions/{id}` - Update division
- `DELETE /api/divisions/{id}` - Delete division
- `POST /api/divisions/{id}/generate-schedule` - Generate match schedule

### Teams
- `GET /api/teams` - Get all teams
- `GET /api/divisions/{divisionId}/teams` - Get teams by division
- `POST /api/divisions/{divisionId}/teams` - Create team
- `PUT /api/teams/{id}` - Update team
- `DELETE /api/teams/{id}` - Delete team

### Venues
- `GET /api/venues` - Get all venues
- `POST /api/venues` - Create venue
- `PUT /api/venues/{id}` - Update venue
- `DELETE /api/venues/{id}` - Delete venue

### Matches
- `GET /api/matches` - Get all matches
- `GET /api/divisions/{divisionId}/matches` - Get matches by division
- `POST /api/matches` - Create match
- `PUT /api/matches/{id}` - Update match
- `PUT /api/matches/{id}/score` - Update match score
- `PUT /api/matches/{id}/complete` - Complete match
- `DELETE /api/matches/{id}` - Delete match

## WebSocket Endpoints

- `/ws` - WebSocket connection endpoint
- `/topic/scores` - Subscribe for live score updates

## Project Structure

```
vorin/
├── backend/                    # Spring Boot backend
│   ├── src/main/java/com/vorin/
│   │   ├── config/            # Configuration classes
│   │   ├── controller/        # REST controllers
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── model/             # JPA entities
│   │   ├── repository/        # JPA repositories
│   │   ├── scheduler/         # Scheduling engine
│   │   ├── security/          # Security components
│   │   └── service/           # Business logic
│   └── pom.xml                # Maven configuration
├── frontend/                   # React frontend
│   ├── src/
│   │   ├── components/        # React components
│   │   ├── context/           # React context
│   │   ├── pages/             # Page components
│   │   ├── services/          # API services
│   │   └── types/             # TypeScript types
│   └── package.json           # NPM configuration
└── docker-compose.yml         # PostgreSQL setup
```

## Development

### Backend Development

```bash
cd backend
./mvnw clean install          # Build the project
./mvnw test                   # Run tests
./mvnw spring-boot:run        # Start development server
```

### Frontend Development

```bash
cd frontend
npm install                   # Install dependencies
npm run dev                   # Start development server
npm run build                 # Build for production
npm run lint                  # Run linter
```

## Configuration

### Backend Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/vorin
spring.datasource.username=vorin_user
spring.datasource.password=vorin_pass

# JWT
jwt.secret=your-secret-key-here
jwt.expiration=86400000

# CORS
cors.allowed-origins=http://localhost:5173
```

### Frontend Configuration

The frontend uses Vite's proxy configuration in `vite.config.ts` to connect to the backend.

## Security

### Authentication
- JWT-based authentication for stateless API
- Passwords are hashed using BCrypt
- Token expiration is configurable (default: 24 hours)

### Security Considerations
- **CSRF Protection**: Disabled for REST API endpoints as the application uses JWT token-based authentication instead of session cookies. CSRF attacks require session cookies to be effective.
- **CORS**: Configured to allow specific origins only (default: http://localhost:5173 for development)
- **SQL Injection**: Protected by using JPA/Hibernate parameterized queries
- **XSS**: Frontend uses React which escapes output by default

### Production Deployment Recommendations
1. Change the JWT secret to a strong, random value
2. Use HTTPS for all communications
3. Configure CORS to allow only your production domain
4. Use environment variables for sensitive configuration
5. Set appropriate database user permissions
6. Enable rate limiting for authentication endpoints
7. Implement proper logging and monitoring

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For support, please open an issue in the GitHub repository.
