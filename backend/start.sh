#!/bin/bash

echo "🚀 Starting VORIN Backend..."
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java 17 or higher is required. Current version: $JAVA_VERSION"
    exit 1
fi

# Check if PostgreSQL is running
if ! nc -z localhost 5432 2>/dev/null; then
    echo "⚠️  PostgreSQL is not running on localhost:5432"
    echo "Please start PostgreSQL using: docker-compose up -d"
    echo ""
fi

# Navigate to backend directory
cd "$(dirname "$0")"

# Run the backend
echo "Starting Spring Boot application..."
./mvnw spring-boot:run
