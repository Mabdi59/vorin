# Contributing to VORIN

Thank you for your interest in contributing to VORIN! This document provides guidelines and instructions for contributing.

## Development Setup

### Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- Docker and Docker Compose
- Git
- Your favorite IDE (IntelliJ IDEA, VS Code, etc.)

### Setting Up the Development Environment

1. **Fork and Clone**
   ```bash
   git clone https://github.com/YOUR_USERNAME/vorin.git
   cd vorin
   ```

2. **Start PostgreSQL**
   ```bash
   docker-compose up -d
   ```

3. **Backend Setup**
   ```bash
   cd backend
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Frontend Setup** (in a new terminal)
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

## Code Style

### Backend (Java)
- Follow standard Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Keep methods small and focused
- Use Lombok annotations to reduce boilerplate

### Frontend (TypeScript/React)
- Use TypeScript for type safety
- Follow React best practices and hooks patterns
- Use functional components
- Keep components small and reusable
- Use meaningful component and variable names

## Making Changes

1. **Create a Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Your Changes**
   - Write clean, maintainable code
   - Add tests for new features
   - Update documentation as needed

3. **Test Your Changes**
   - Run backend tests: `cd backend && ./mvnw test`
   - Run frontend build: `cd frontend && npm run build`
   - Manually test the functionality

4. **Commit Your Changes**
   ```bash
   git add .
   git commit -m "Add descriptive commit message"
   ```

5. **Push to Your Fork**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**
   - Go to the original repository
   - Click "New Pull Request"
   - Select your branch
   - Provide a clear description of your changes

## Areas for Contribution

### High Priority
- [ ] Add comprehensive test coverage
- [ ] Implement team member management
- [ ] Add bracket visualization components
- [ ] Implement tournament bracket advancement logic
- [ ] Add match scheduling conflict detection
- [ ] Implement email notifications
- [ ] Add user profile management

### Medium Priority
- [ ] Add tournament search and filtering
- [ ] Implement tournament registration system
- [ ] Add statistics and analytics dashboard
- [ ] Implement playoff bracket generation
- [ ] Add multi-language support
- [ ] Implement role-based access control
- [ ] Add tournament templates

### Nice to Have
- [ ] Mobile app (React Native)
- [ ] Tournament live streaming integration
- [ ] Social media sharing
- [ ] Tournament leaderboards
- [ ] Payment integration for entry fees
- [ ] Tournament merchandise management
- [ ] Mobile-responsive improvements

## Testing

### Backend Testing
```bash
cd backend
./mvnw test                    # Run all tests
./mvnw test -Dtest=TestName    # Run specific test
```

### Frontend Testing
```bash
cd frontend
npm run build    # Verify build works
npm run lint     # Check code style
```

## Documentation

When adding new features:
- Update the README.md
- Add API documentation for new endpoints
- Update the database schema documentation if applicable
- Add inline comments for complex logic

## Code Review Process

All pull requests will be reviewed by maintainers. We look for:
- Code quality and style
- Test coverage
- Documentation
- Performance impact
- Security considerations

## Questions or Issues?

- Open an issue for bugs or feature requests
- Use discussions for questions and ideas
- Join our community chat (if available)

Thank you for contributing to VORIN! 🎉
