-- VORIN Sample Data
-- This script creates sample data for testing the VORIN tournament management system

-- Create sample users (passwords are BCrypt hashed for 'password123')
INSERT INTO users (username, email, password, created_at) VALUES
('admin', 'admin@vorin.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCy', NOW()),
('organizer1', 'organizer1@vorin.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCy', NOW()),
('organizer2', 'organizer2@vorin.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCy', NOW());

-- Assign roles to users
INSERT INTO user_roles (user_id, role) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(2, 'ROLE_USER'),
(3, 'ROLE_USER');

-- Create sample venues
INSERT INTO venues (name, address, capacity, facilities, created_at) VALUES
('Central Sports Arena', '123 Main St, Downtown', 5000, 'Parking, Food Court, WiFi, Medical Staff', NOW()),
('Riverside Stadium', '456 River Rd, Riverside', 3000, 'Parking, Concessions, Locker Rooms', NOW()),
('Community Sports Center', '789 Oak Ave, Northside', 1500, 'Parking, Cafeteria, Training Rooms', NOW());

-- Create sample tournaments
INSERT INTO tournaments (name, description, start_date, end_date, status, created_by, created_at) VALUES
('Spring Championship 2024', 'Annual spring tournament featuring multiple divisions', '2024-04-01', '2024-04-15', 'UPCOMING', 1, NOW()),
('Summer League 2024', 'Recreational summer tournament for all skill levels', '2024-06-01', '2024-08-31', 'UPCOMING', 2, NOW());

-- Create divisions for Spring Championship
INSERT INTO divisions (name, description, tournament_id, bracket_type) VALUES
('Men''s Open', 'Open division for men', 1, 'SINGLE_ELIMINATION'),
('Women''s Open', 'Open division for women', 1, 'ROUND_ROBIN'),
('Mixed Doubles', 'Mixed doubles competition', 1, 'ROUND_ROBIN');

-- Create divisions for Summer League
INSERT INTO divisions (name, description, tournament_id, bracket_type) VALUES
('Recreational A', 'Advanced recreational players', 2, 'ROUND_ROBIN'),
('Recreational B', 'Intermediate recreational players', 2, 'ROUND_ROBIN');

-- Create sample teams for Men's Open division
INSERT INTO teams (name, description, division_id, wins, losses, draws, points, created_at) VALUES
('Thunder Strikers', 'Defending champions', 1, 0, 0, 0, 0, NOW()),
('Lightning Bolts', 'Fast-paced offensive team', 1, 0, 0, 0, 0, NOW()),
('Storm Chasers', 'Defensive powerhouse', 1, 0, 0, 0, 0, NOW()),
('Blazing Phoenix', 'Young and energetic squad', 1, 0, 0, 0, 0, NOW());

-- Create sample teams for Women's Open division
INSERT INTO teams (name, description, division_id, wins, losses, draws, points, created_at) VALUES
('Victory Valkyries', 'Experienced champions', 2, 0, 0, 0, 0, NOW()),
('Dynamic Divas', 'Skilled all-around team', 2, 0, 0, 0, 0, NOW()),
('Power Panthers', 'Strong and strategic', 2, 0, 0, 0, 0, NOW()),
('Swift Swans', 'Quick and coordinated', 2, 0, 0, 0, 0, NOW());

-- Create sample teams for Mixed Doubles
INSERT INTO teams (name, description, division_id, wins, losses, draws, points, created_at) VALUES
('Dynamic Duo', 'Perfect chemistry', 3, 0, 0, 0, 0, NOW()),
('Power Pair', 'Balanced offense and defense', 3, 0, 0, 0, 0, NOW()),
('Team Synergy', 'Great communication', 3, 0, 0, 0, 0, NOW());

-- Create sample matches for Women's Open (Round Robin)
INSERT INTO matches (division_id, team1_id, team2_id, venue_id, scheduled_time, team1_score, team2_score, status, round_number, created_at, updated_at) VALUES
(2, 5, 6, 1, '2024-04-01 10:00:00', 0, 0, 'SCHEDULED', 1, NOW(), NOW()),
(2, 7, 8, 1, '2024-04-01 12:00:00', 0, 0, 'SCHEDULED', 1, NOW(), NOW()),
(2, 5, 7, 2, '2024-04-02 10:00:00', 0, 0, 'SCHEDULED', 2, NOW(), NOW()),
(2, 6, 8, 2, '2024-04-02 12:00:00', 0, 0, 'SCHEDULED', 2, NOW(), NOW()),
(2, 5, 8, 1, '2024-04-03 10:00:00', 0, 0, 'SCHEDULED', 3, NOW(), NOW()),
(2, 6, 7, 1, '2024-04-03 12:00:00', 0, 0, 'SCHEDULED', 3, NOW(), NOW());

-- Create sample matches for Men's Open (Single Elimination)
INSERT INTO matches (division_id, team1_id, team2_id, venue_id, scheduled_time, team1_score, team2_score, status, round_number, bracket_position, created_at, updated_at) VALUES
(1, 1, 2, 1, '2024-04-05 14:00:00', 0, 0, 'SCHEDULED', 1, 0, NOW(), NOW()),
(1, 3, 4, 2, '2024-04-05 16:00:00', 0, 0, 'SCHEDULED', 1, 1, NOW(), NOW());
