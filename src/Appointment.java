public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String date;

    public Appointment(int appointmentId, int patientId, int doctorId, String date) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId +
               ", Patient ID: " + patientId +
               ", Doctor ID: " + doctorId +
               ", Date: " + date;
    }
}