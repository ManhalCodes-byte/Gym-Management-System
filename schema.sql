CREATE DATABASE gymdb;
GO
USE gymdb;
GO

CREATE TABLE members (
    member_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    membership_type VARCHAR(50),
    join_date DATE,
    is_active BIT DEFAULT 1
);

CREATE TABLE trainers (
    trainer_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    specialization VARCHAR(100),
    salary_per_hour FLOAT
);

CREATE TABLE staff (
    staff_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    position VARCHAR(100),
    salary FLOAT
);

CREATE TABLE memberships (
    membership_id INT IDENTITY(1,1) PRIMARY KEY,
    member_id INT,
    plan_name VARCHAR(50),
    start_date DATE,
    end_date DATE,
    fee FLOAT,
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

CREATE TABLE workout_classes (
    class_id INT IDENTITY(1,1) PRIMARY KEY,
    class_name VARCHAR(100),
    trainer_id INT,
    schedule DATETIME,
    capacity INT,
    FOREIGN KEY (trainer_id) REFERENCES trainers(trainer_id)
);

CREATE TABLE class_enrollment (
    enrollment_id INT IDENTITY(1,1) PRIMARY KEY,
    class_id INT,
    member_id INT,
    FOREIGN KEY (class_id) REFERENCES workout_classes(class_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

CREATE TABLE equipment (
    equipment_id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100),
    quantity INT,
    condition VARCHAR(50),
    last_maintenance DATE
);

CREATE TABLE payments (
    payment_id INT IDENTITY(1,1) PRIMARY KEY,
    member_id INT,
    amount FLOAT,
    payment_date DATE,
    method VARCHAR(50),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);