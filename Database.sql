SET PASSWORD = PASSWORD('MyTeam');
SHOW DATABASES;
USE pii2_SmartSphere;
CREATE TABLE User (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Username VARCHAR(50) UNIQUE NOT NULL,
    PasswordHash VARCHAR(255) NOT NULL, -- hashed/encrypted
    Email VARCHAR(100),
    PhoneNumber VARCHAR(20),
    Role ENUM('Homeowner', 'Technician', 'Security Guard') NOT NULL,
    IsActive BOOLEAN DEFAULT TRUE
);
CREATE TABLE Home (
    HomeId INT AUTO_INCREMENT PRIMARY KEY,
    Address VARCHAR(255) NOT NULL,
    OwnerId INT NOT NULL,
    FOREIGN KEY (OwnerId) REFERENCES User(UserId)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Sensor (
    SensorId INT AUTO_INCREMENT PRIMARY KEY,
    Type ENUM('temperature', 'motion', 'humidity', 'light') NOT NULL,
    HomeId INT NOT NULL,
    FOREIGN KEY (HomeId) REFERENCES Home(HomeId)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Reading (
    ReadingId INT AUTO_INCREMENT PRIMARY KEY,
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    Value DECIMAL(10,2) NOT NULL,
    SensorId INT NOT NULL,
    Snapshot LONGBLOB,
    FOREIGN KEY (SensorId) REFERENCES Sensor(SensorId)
        ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE Log (
    LogId INT AUTO_INCREMENT PRIMARY KEY,
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    Action VARCHAR(255) NOT NULL,
    UserId INT NOT NULL,
    HomeId INT NOT NULL,
    FOREIGN KEY (UserId) REFERENCES User(UserId),
    FOREIGN KEY (HomeId) REFERENCES Home(HomeId)
);
CREATE TABLE Alert (
    AlertId INT AUTO_INCREMENT PRIMARY KEY,
    Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    Message VARCHAR(255),
    HomeId INT NOT NULL,
    FOREIGN KEY (HomeId) REFERENCES Home(HomeId)
);
-- Create a user
INSERT INTO User (FirstName, LastName, Username, PasswordHash, Role)
VALUES ('Alice', 'Smith', 'alice123', SHA2('mypassword', 256), 'Homeowner');

-- Create a home
INSERT INTO Home (Address, OwnerId)
VALUES ('123 Main St', 1);

-- Add a sensor
INSERT INTO Sensor (Type, HomeId)
VALUES ('temperature', 1);

-- Add a reading
INSERT INTO Reading (Value, SensorId)
VALUES (25.7, 1);

-- Add a log
INSERT INTO Log (Action, UserId, HomeId)
VALUES ('User login', 1, 1);

-- Add an alert
INSERT INTO Alert (Message, HomeId)
VALUES ('High temperature detected', 1);

SELECT * FROM Home;
INSERT INTO User (FirstName, LastName, Username, PasswordHash, Role)
VALUES (
  'John', 'Doe', 'johndoe', SHA2('password123', 256), 'Homeowner'
);
SELECT * FROM User;

INSERT INTO Home (Address, OwnerId)
VALUES ('123 Main Street', 1);  -- or the actual UserId from above

-- Create a user
INSERT INTO User (FirstName, LastName, Username, PasswordHash, Role)
VALUES ('Alice', 'Smith', 'alice123', SHA2('mypassword', 256), 'Homeowner');

-- Create a home
INSERT INTO Home (Address, OwnerId)
VALUES ('123 Main St', 1);

-- Add a sensor
INSERT INTO Sensor (Type, HomeId)
VALUES ('temperature', 1);

-- Add a reading
INSERT INTO Reading (Value, SensorId)
VALUES (25.7, 1);

-- Add a log
INSERT INTO Log (Action, UserId, HomeId)
VALUES ('User login', 1, 1);

-- Add an alert
INSERT INTO Alert (Message, HomeId)
VALUES ('High temperature detected', 1);










