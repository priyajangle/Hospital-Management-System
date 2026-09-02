import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("Patient added successfully.");
    }

    static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        System.out.println("\n--- PATIENTS ---");
        for (Patient p : patients) System.out.println(p);
    }

    static void searchPatient() {
        System.out.print("Enter patient ID: ");
        int id = readInt();

        for (Patient p : patients) {
            if (p.getId() == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    static void updatePatient() {
        System.out.print("Enter patient ID: ");
        int id = readInt();

        for (Patient p : patients) {
            if (p.getId() == id) {
                System.out.print("Enter new name: ");
                p.setName(sc.nextLine());
                System.out.print("Enter new age: ");
                p.setAge(readInt());
                System.out.print("Enter new gender: ");
                p.setGender(sc.nextLine());
                System.out.print("Enter new phone: ");
                p.setPhone(sc.nextLine());

                System.out.println("Patient updated successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    static void deletePatient() {
        System.out.print("Enter patient ID: ");
        int id = readInt();

        for (Patient p : patients) {
            if (p.getId() == id) {
                patients.remove(p);
                System.out.println("Patient deleted successfully.");
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = sc.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = sc.nextLine();

        doctors.add(new Doctor(doctorId++, name, specialization));
        System.out.println("Doctor added successfully.");
    }

    static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        System.out.println("\n--- DOCTORS ---");
        for (Doctor d : doctors) System.out.println(d);
    }

    static void bookAppointment() {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Add at least one patient and one doctor first.");
            return;
        }

        System.out.print("Enter patient ID: ");
        int pid = readInt();
        System.out.print("Enter doctor ID: ");
        int did = readInt();
        System.out.print("Enter appointment date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        boolean patientExists = patients.stream().anyMatch(p -> p.getId() == pid);
        boolean doctorExists = doctors.stream().anyMatch(d -> d.getId() == did);

        if (patientExists && doctorExists) {
            appointments.add(new Appointment(appointmentId++, pid, did, date));
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("Invalid patient or doctor ID.");
        }
    }

    static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        System.out.println("\n--- APPOINTMENTS ---");
        for (Appointment a : appointments) System.out.println(a);
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