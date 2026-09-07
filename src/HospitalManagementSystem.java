import java.util.ArrayList;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class HospitalManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    static int patientId = 1;
    static int doctorId = 1;
    static int appointmentId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Add Doctor");
            System.out.println("7. View Doctors");
            System.out.println("8. Book Appointment");
            System.out.println("9. View Appointments");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> viewPatients();
                case 3 -> searchPatient();
                case 4 -> updatePatient();
                case 5 -> deletePatient();
                case 6 -> addDoctor();
                case 7 -> viewDoctors();
                case 8 -> bookAppointment();
                case 9 -> viewAppointments();
                case 10 -> {
                    System.out.println("Thank you for using the system!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = readInt();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        patients.add(new Patient(patientId++, name, age, gender, phone));

     try {
         Connection con = DBConnection.getConnection();

         String sql = "INSERT INTO patients (name, age, gender, phone) VALUES (?, ?, ?, ?)";

         PreparedStatement ps = con.prepareStatement(sql);

         ps.setString(1, name);
         ps.setInt(2, age);
         ps.setString(3, gender);
         ps.setString(4, phone);

         ps.executeUpdate();

         System.out.println("Patient saved in database successfully!");

         ps.close();
         con.close();

     } catch (SQLException e) {
         System.out.println("Database insert failed!");
         e.printStackTrace();
     }
        System.out.println("Patient added successfully.");
    }

    static void viewPatients() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM patients";

            PreparedStatement ps = con.prepareStatement(sql);

            var rs = ps.executeQuery();

            System.out.println("\n--- PATIENTS ---");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("patient_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("age") + " | " +
                    rs.getString("gender") + " | " +
                    rs.getString("phone")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Database view failed!");
            e.printStackTrace();
        }
    }

    static void searchPatient() {

        System.out.print("Enter patient ID: ");

        int id = readInt();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM patients WHERE patient_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            var rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n--- PATIENT FOUND ---");

                System.out.println("ID: " + rs.getInt("patient_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Phone: " + rs.getString("phone"));

            } else {

                System.out.println("Patient not found.");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Database search failed!");
            e.printStackTrace();

        }
    }

    static void updatePatient() {

        System.out.print("Enter patient ID: ");

        int id = readInt();

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new age: ");
        int age = readInt();

        System.out.print("Enter new gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, phone = ? WHERE patient_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, phone);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("Patient not found.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Database update failed!");
            e.printStackTrace();

        }
    }

    static void deletePatient() {

        System.out.print("Enter patient ID: ");

        int id = readInt();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM patients WHERE patient_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Patient not found.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Database delete failed!");
            e.printStackTrace();

        }
    }
    static void addDoctor() {

        System.out.print("Enter doctor name: ");
        String name = sc.nextLine();

        System.out.print("Enter specialization: ");
        String specialization = sc.nextLine();

        doctors.add(new Doctor(doctorId++, name, specialization));

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO doctors (name, specialization) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, specialization);

            ps.executeUpdate();

            System.out.println("Doctor saved in database successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Database insert failed!");
            e.printStackTrace();

        }

        System.out.println("Doctor added successfully.");
    }
    static void viewDoctors() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM doctors";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- DOCTORS ---");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("doctor_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("specialization")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Database view failed!");
            e.printStackTrace();
        }
    }

    static void bookAppointment() {

        System.out.print("Enter patient ID: ");
        int pid = readInt();

        System.out.print("Enter doctor ID: ");
        int did = readInt();

        System.out.print("Enter appointment date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        try {

            Connection con = DBConnection.getConnection();

            // Check patient
            String patientSql = "SELECT * FROM patients WHERE patient_id = ?";
            PreparedStatement patientPs = con.prepareStatement(patientSql);
            patientPs.setInt(1, pid);

            ResultSet patientRs = patientPs.executeQuery();

            if (!patientRs.next()) {
                System.out.println("Invalid patient ID.");
                patientRs.close();
                patientPs.close();
                con.close();
                return;
            }

        
            String doctorSql = "SELECT * FROM doctors WHERE doctor_id = ?";
            PreparedStatement doctorPs = con.prepareStatement(doctorSql);
            doctorPs.setInt(1, did);

            ResultSet doctorRs = doctorPs.executeQuery();

            if (!doctorRs.next()) {
                System.out.println("Invalid doctor ID.");
                patientRs.close();
                patientPs.close();
                doctorRs.close();
                doctorPs.close();
                con.close();
                return;
            }

            
            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pid);
            ps.setInt(2, did);
            ps.setString(3, date);

            ps.executeUpdate();

            System.out.println("Appointment booked successfully!");

            patientRs.close();
            patientPs.close();
            doctorRs.close();
            doctorPs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Database appointment failed!");
            e.printStackTrace();

        }
    }
    static void viewAppointments() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT a.appointment_id, p.name AS patient_name, " +
                         "d.name AS doctor_name, a.appointment_date " +
                         "FROM appointments a " +
                         "JOIN patients p ON a.patient_id = p.patient_id " +
                         "JOIN doctors d ON a.doctor_id = d.doctor_id";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- APPOINTMENTS ---");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("appointment_id") + " | " +
                    "Patient: " + rs.getString("patient_name") + " | " +
                    "Doctor: " + rs.getString("doctor_name") + " | " +
                    "Date: " + rs.getString("appointment_date")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Database view failed!");
            e.printStackTrace();

        }
    }
    static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}