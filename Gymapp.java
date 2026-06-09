package com.gym;

import java.sql.*;
import java.util.Scanner;

public class GymApp {

    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=gymdb;encrypt=false";
    static final String USER = "sa";
    static final String PASSWORD = "yourpassword";

    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Database!");
            mainMenu();
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n==============================");
            System.out.println("     GYM MANAGEMENT SYSTEM    ");
            System.out.println("==============================");
            System.out.println("1. Member Management");
            System.out.println("2. Trainer Management");
            System.out.println("3. Membership Plans");
            System.out.println("4. Payment Management");
            System.out.println("5. Equipment Tracking");
            System.out.println("6. Workout Classes");
            System.out.println("0. Exit");
            System.out.println("==============================");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> memberMenu();
                case 2 -> trainerMenu();
                case 3 -> membershipMenu();
                case 4 -> paymentMenu();
                case 5 -> equipmentMenu();
                case 6 -> workoutClassMenu();
                case 0 -> { System.out.println("Goodbye!"); System.exit(0); }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ---- MEMBER MENU ----
    static void memberMenu() {
        System.out.println("\n--- MEMBER MANAGEMENT ---");
        System.out.println("1. Add Member");
        System.out.println("2. View All Members");
        System.out.println("3. Update Member");
        System.out.println("4. Delete Member");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        switch (c) {
            case 1 -> addMember();
            case 2 -> viewMembers();
            case 3 -> updateMember();
            case 4 -> deleteMember();
        }
    }

    static void addMember() {
        sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Phone: "); String phone = sc.nextLine();
        System.out.print("Membership Type (Standard/Premium): "); String type = sc.nextLine();

        String sql = "INSERT INTO members (name, email, phone, membership_type, join_date, is_active) VALUES (?, ?, ?, ?, GETDATE(), 1)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, type);
            ps.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewMembers() {
        String sql = "SELECT * FROM members";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL MEMBERS ---");
            System.out.printf("%-5s %-20s %-25s %-15s %-12s %-8s%n",
                    "ID", "Name", "Email", "Phone", "Type", "Active");
            System.out.println("-".repeat(90));
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-25s %-15s %-12s %-8s%n",
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("membership_type"),
                        rs.getBoolean("is_active") ? "Yes" : "No");
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void updateMember() {
        sc.nextLine();
        System.out.print("Enter Member ID to update: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("New Name: "); String name = sc.nextLine();
        System.out.print("New Email: "); String email = sc.nextLine();
        System.out.print("New Phone: "); String phone = sc.nextLine();

        String sql = "UPDATE members SET name=?, email=?, phone=? WHERE member_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Member updated!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void deleteMember() {
        System.out.print("Enter Member ID to delete: "); int id = sc.nextInt();
        String sql = "DELETE FROM members WHERE member_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Member deleted!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    // ---- TRAINER MENU ----
    static void trainerMenu() {
        System.out.println("\n--- TRAINER MANAGEMENT ---");
        System.out.println("1. Add Trainer");
        System.out.println("2. View All Trainers");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        switch (c) {
            case 1 -> addTrainer();
            case 2 -> viewTrainers();
        }
    }

    static void addTrainer() {
        sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Specialization: "); String spec = sc.nextLine();
        System.out.print("Salary per Hour: "); double salary = sc.nextDouble();

        String sql = "INSERT INTO trainers (name, email, specialization, salary_per_hour) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name); ps.setString(2, email);
            ps.setString(3, spec); ps.setDouble(4, salary);
            ps.executeUpdate();
            System.out.println("Trainer added!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewTrainers() {
        String sql = "SELECT * FROM trainers";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL TRAINERS ---");
            System.out.printf("%-5s %-20s %-25s %-20s %-10s%n", "ID", "Name", "Email", "Specialization", "Salary/hr");
            System.out.println("-".repeat(85));
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-25s %-20s %-10.2f%n",
                        rs.getInt("trainer_id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("specialization"),
                        rs.getDouble("salary_per_hour"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    // ---- MEMBERSHIP MENU ----
    static void membershipMenu() {
        System.out.println("\n--- MEMBERSHIP PLANS ---");
        System.out.println("1. Assign Membership to Member");
        System.out.println("2. View All Memberships");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        switch (c) {
            case 1 -> assignMembership();
            case 2 -> viewMemberships();
        }
    }

    static void assignMembership() {
        sc.nextLine();
        System.out.print("Member ID: "); int memberId = sc.nextInt(); sc.nextLine();
        System.out.print("Plan (Standard/Premium): "); String plan = sc.nextLine();
        System.out.print("Duration (months): "); int months = sc.nextInt();
        double fee = plan.equalsIgnoreCase("Premium") ? 60.0 * months : 30.0 * months;

        String sql = "INSERT INTO memberships (member_id, plan_name, start_date, end_date, fee) VALUES (?, ?, GETDATE(), DATEADD(month,?,GETDATE()), ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId); ps.setString(2, plan);
            ps.setInt(3, months); ps.setDouble(4, fee);
            ps.executeUpdate();
            System.out.println("Membership assigned! Fee: $" + fee);
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewMemberships() {
        String sql = "SELECT m.membership_id, mb.name, m.plan_name, m.start_date, m.end_date, m.fee FROM memberships m JOIN members mb ON m.member_id = mb.member_id";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL MEMBERSHIPS ---");
            System.out.printf("%-5s %-20s %-12s %-12s %-12s %-8s%n", "ID", "Member", "Plan", "Start", "End", "Fee");
            System.out.println("-".repeat(75));
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-12s %-12s %-12s $%-8.2f%n",
                        rs.getInt("membership_id"), rs.getString("name"),
                        rs.getString("plan_name"), rs.getDate("start_date"),
                        rs.getDate("end_date"), rs.getDouble("fee"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    // ---- PAYMENT MENU ----
    static void paymentMenu() {
        System.out.println("\n--- PAYMENT MANAGEMENT ---");
        System.out.println("1. Record Payment");
        System.out.println("2. View All Payments");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        switch (c) {
            case 1 -> recordPayment();
            case 2 -> viewPayments();
        }
    }

    static void recordPayment() {
        sc.nextLine();
        System.out.print("Member ID: "); int memberId = sc.nextInt();
        System.out.print("Amount: "); double amount = sc.nextDouble(); sc.nextLine();
        System.out.print("Payment Method (Cash/Card/Online): "); String method = sc.nextLine();

        String sql = "INSERT INTO payments (member_id, amount, payment_date, method) VALUES (?, ?, GETDATE(), ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ps.setDouble(2, amount);
            ps.setString(3, method);
            ps.executeUpdate();
            System.out.println("Payment recorded!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewPayments() {
        String sql = "SELECT p.payment_id, m.name, p.amount, p.payment_date, p.method FROM payments p JOIN members m ON p.member_id = m.member_id";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL PAYMENTS ---");
            System.out.printf("%-5s %-20s %-10s %-12s %-10s%n", "ID", "Member", "Amount", "Date", "Method");
            System.out.println("-".repeat(62));
            while (rs.next()) {
                System.out.printf("%-5d %-20s $%-10.2f %-12s %-10s%n",
                        rs.getInt("payment_id"), rs.getString("name"),
                        rs.getDouble("amount"), rs.getDate("payment_date"),
                        rs.getString("method"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    // ---- EQUIPMENT MENU ----
    static void equipmentMenu() {
        System.out.println("\n--- EQUIPMENT TRACKING ---");
        System.out.println("1. Add Equipment");
        System.out.println("2. View All Equipment");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        switch (c) {
            case 1 -> addEquipment();
            case 2 -> viewEquipment();
        }
    }

    static void addEquipment() {
        sc.nextLine();
        System.out.print("Equipment Name: "); String name = sc.nextLine();
        System.out.print("Quantity: "); int qty = sc.nextInt(); sc.nextLine();
        System.out.print("Condition (Good/Fair/Needs Repair): "); String condition = sc.nextLine();

        String sql = "INSERT INTO equipment (name, quantity, condition, last_maintenance) VALUES (?, ?, ?, GETDATE())";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, qty);
            ps.setString(3, condition);
            ps.executeUpdate();
            System.out.println("Equipment added!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewEquipment() {
        String sql = "SELECT * FROM equipment";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL EQUIPMENT ---");
            System.out.printf("%-5s %-20s %-10s %-15s %-12s%n", "ID", "Name", "Quantity", "Condition", "Last Maintenance");
            System.out.println("-".repeat(65));
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-10d %-15s %-12s%n",
                        rs.getInt("equipment_id"), rs.getString("name"),
                        rs.getInt("quantity"), rs.getString("condition"),
                        rs.getDate("last_maintenance"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    // ---- WORKOUT CLASS MENU ----
    static void workoutClassMenu() {
        System.out.println("\n--- WORKOUT CLASSES ---");
        System.out.println("1. Add Workout Class");
        System.out.println("2. View All Classes");
        System.out.println("3. Enroll Member in Class");
        System.out.println("0. Back");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        switch (c) {
            case 1 -> addWorkoutClass();
            case 2 -> viewWorkoutClasses();
            case 3 -> enrollMember();
        }
    }

    static void addWorkoutClass() {
        sc.nextLine();
        System.out.print("Class Name: "); String name = sc.nextLine();
        System.out.print("Trainer ID: "); int trainerId = sc.nextInt();
        System.out.print("Capacity: "); int capacity = sc.nextInt(); sc.nextLine();
        System.out.print("Schedule (YYYY-MM-DD HH:MM): "); String schedule = sc.nextLine();

        String sql = "INSERT INTO workout_classes (class_name, trainer_id, schedule, capacity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, trainerId);
            ps.setString(3, schedule);
            ps.setInt(4, capacity);
            ps.executeUpdate();
            System.out.println("Workout class added!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void viewWorkoutClasses() {
        String sql = "SELECT wc.class_id, wc.class_name, t.name AS trainer, wc.schedule, wc.capacity FROM workout_classes wc JOIN trainers t ON wc.trainer_id = t.trainer_id";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            System.out.println("\n--- ALL WORKOUT CLASSES ---");
            System.out.printf("%-5s %-20s %-20s %-20s %-10s%n", "ID", "Class", "Trainer", "Schedule", "Capacity");
            System.out.println("-".repeat(78));
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-20s %-20s %-10d%n",
                        rs.getInt("class_id"), rs.getString("class_name"),
                        rs.getString("trainer"), rs.getTimestamp("schedule"),
                        rs.getInt("capacity"));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void enrollMember() {
        System.out.print("Member ID: "); int memberId = sc.nextInt();
        System.out.print("Class ID: "); int classId = sc.nextInt();

        String sql = "INSERT INTO class_enrollment (class_id, member_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, classId);
            ps.setInt(2, memberId);
            ps.executeUpdate();
            System.out.println("Member enrolled in class!");
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }
}