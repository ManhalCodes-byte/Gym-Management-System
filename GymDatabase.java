package com.gym.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymDatabase {

    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=gymdb;encrypt=false;integratedSecurity=true";
    static final String USER = "";
    static final String PASSWORD = "";

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to database!");
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    // ---- MEMBER OPERATIONS ----

    public void addMember(String name, String email, String phone, String membershipType) {
        String sql = "INSERT INTO members (name, email, phone, membership_type, join_date, is_active) VALUES (?, ?, ?, ?, GETDATE(), 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, membershipType);
            ps.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    public ResultSet getAllMembers() {
        try {
            Statement st = connection.createStatement();
            return st.executeQuery("SELECT * FROM members");
        } catch (SQLException e) {
            System.out.println("Error fetching members: " + e.getMessage());
            return null;
        }
    }

    public void updateMember(int id, String name, String email, String phone) {
        String sql = "UPDATE members SET name=?, email=?, phone=? WHERE member_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Member updated!");
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM members WHERE member_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Member deleted!");
        } catch (SQLException e) {
            System.out.println("Error deleting member: " + e.getMessage());
        }
    }

    // ---- TRAINER OPERATIONS ----

    public void addTrainer(String name, String email, String specialization, double salary) {
        String sql = "INSERT INTO trainers (name, email, specialization, salary_per_hour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, specialization);
            ps.setDouble(4, salary);
            ps.executeUpdate();
            System.out.println("Trainer added!");
        } catch (SQLException e) {
            System.out.println("Error adding trainer: " + e.getMessage());
        }
    }

    public ResultSet getAllTrainers() {
        try {
            Statement st = connection.createStatement();
            return st.executeQuery("SELECT * FROM trainers");
        } catch (SQLException e) {
            System.out.println("Error fetching trainers: " + e.getMessage());
            return null;
        }
    }

    // ---- MEMBERSHIP OPERATIONS ----

    public void assignMembership(int memberId, String planName, int months) {
        double fee = planName.equalsIgnoreCase("Premium") ? 60.0 * months : 30.0 * months;
        String sql = "INSERT INTO memberships (member_id, plan_name, start_date, end_date, fee) VALUES (?, ?, GETDATE(), DATEADD(month,?,GETDATE()), ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ps.setString(2, planName);
            ps.setInt(3, months);
            ps.setDouble(4, fee);
            ps.executeUpdate();
            System.out.println("Membership assigned! Fee: $" + fee);
        } catch (SQLException e) {
            System.out.println("Error assigning membership: " + e.getMessage());
        }
    }

    public ResultSet getAllMemberships() {
        try {
            Statement st = connection.createStatement();
            return st.executeQuery(
                "SELECT m.membership_id, mb.name, m.plan_name, m.start_date, m.end_date, m.fee " +
                "FROM memberships m JOIN members mb ON m.member_id = mb.member_id"
            );
        } catch (SQLException e) {
            System.out.println("Error fetching memberships: " + e.getMessage());
            return null;
        }
    }

    // ---- PAYMENT OPERATIONS ----

    public void recordPayment(int memberId, double amount, String method) {
        String sql = "INSERT INTO payments (member_id, amount, payment_date, method) VALUES (?, ?, GETDATE(), ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ps.setDouble(2, amount);
            ps.setString(3, method);
            ps.executeUpdate();
            System.out.println("Payment recorded!");
        } catch (SQLException e) {
            System.out.println("Error recording payment: " + e.getMessage());
        }
    }

    public ResultSet getAllPayments() {
        try {
            Statement st = connection.createStatement();
            return st.executeQuery(
                "SELECT p.payment_id, m.name, p.amount, p.payment_date, p.method " +
                "FROM payments p JOIN members m ON p.member_id = m.member_id"
            );
        } catch (SQLException e) {
            System.out.println("Error fetching payments: " + e.getMessage());
            return null;
        }
    }

    // ---- EQUIPMENT OPERATIONS ----

    public void addEquipment(String name, int quantity, String condition) {
        String sql = "INSERT INTO equipment (name, quantity, condition, last_maintenance) VALUES (?, ?, ?, GETDATE())";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setString(3, condition);
            ps.executeUpdate();
            System.out.println("Equipment added!");
        } catch (SQLException e) {
            System.out.println("Error adding equipment: " + e.getMessage());
        }
    }

    public ResultSet getAllEquipment() {
        try {
            Statement st = connection.createStatement();
            return st.executeQuery("SELECT * FROM equipment");
        } catch (SQLException e) {
            System.out.println("Error fetching equipment: " + e.getMessage());
            return null;
        }
    }

    // ---- WORKOUT CLASS OPERATIONS ----

    public void addWorkoutClass(String className, int trainerId, String schedule, int capacity) {
        String sql = "INSERT INTO workout_classes (class_name, trainer_id, schedule, capacity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, className);
            ps.setInt(2, trainerId);
            ps.setString(3, schedule);
            ps.setInt(4, capacity);
            ps.executeUpdate();
            System.out.println("Workout class added!");
        } catch (SQLException e) {
            System.out.println("Error adding workout class: " + e.getMessage());
        }
    }

    public ResultSet getAllWorkoutClasses() {
        try {
            Statement st = connection.createStatement();
            return st.executeQuery(
                "SELECT wc.class_id, wc.class_name, t.name AS trainer, wc.schedule, wc.capacity " +
                "FROM workout_classes wc JOIN trainers t ON wc.trainer_id = t.trainer_id"
            );
        } catch (SQLException e) {
            System.out.println("Error fetching workout classes: " + e.getMessage());
            return null;
        }
    }

    public void enrollMember(int classId, int memberId) {
        String sql = "INSERT INTO class_enrollment (class_id, member_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, classId);
            ps.setInt(2, memberId);
            ps.executeUpdate();
            System.out.println("Member enrolled in class!");
        } catch (SQLException e) {
            System.out.println("Error enrolling member: " + e.getMessage());
        }
    }
}