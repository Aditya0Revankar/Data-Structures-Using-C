import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.exit;
class Faculty {
    private int facultyId;
    private String name;
    private String department;
    private String designation;
    private String address;
    private String gender;

    public Faculty(int facultyId, String name, String department, String designation, String address, String gender) {
        this.facultyId = facultyId;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.address = address;
        this.gender = gender;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

class TeachingFaculty extends Faculty {
    static int cl=15, el=10, dl=5;
    public TeachingFaculty(int facultyId, String name, String department, String designation, String address, String gender) {
        super(facultyId, name, department, designation, address, gender);
    }

    public void grantCasualLeave(int casualLeave){
        ResetLeave();
        if(casualLeave > cl)
            System.out.println("Insufficient casual leave");
        else
        if(casualLeave>5) {
            System.out.println("Cannot Assign Casual Leave more than 5 in a row.\nEnter Valid number.");
            exit(0);
        }
        else
            cl -= casualLeave;
    }
    public void grantEarnedLeave(int earnedLeave){
        ResetLeave();
        if(earnedLeave > el) {
            System.out.println("Insufficient earned leave");
            exit(0);
        }
        else
            el -= earnedLeave;
    }
    public void grantDutyLeave(int dutyLeave){
        ResetLeave();
        if(dutyLeave > dl) {
            System.out.println("Insufficient duty leave");
            exit(0);
        }
        else
            dl -= dutyLeave;
    }
    public void display(){
        System.out.println("\nID: " + getFacultyId() + "\nName: " + getName() + "\nDepartment: " + getDepartment() + "\nDesignation: " + getDesignation() + "\nAddress: " + getAddress() + "\nGender: " + getGender());
        System.out.println("\nRemaining Leaves: \n1.Causal: " + cl + "\n2.Earned: " + el + "\n3.Duty: " + dl);
    }

    public void ResetLeave(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM");
        String formattedDate = myDateObj.format(myFormatObj);
        String resetDate = "01/01";
        if(resetDate == formattedDate) {
            cl = 15;
            el = 10;
            dl = 5;
        }
    }
}

class NonTeachingFaculty extends Faculty {
    static int el=10;
    public NonTeachingFaculty(int facultyId, String name, String department, String designation, String address, String gender) {
        super(facultyId, name, department, designation, address, gender);
    }
    public void grantEarnedLeave(int earnedLeave){
        ResetLeave();
        if(earnedLeave > el) {
            System.out.println("Insufficient earned leave");
            exit(0);
        }
        else
            el -= earnedLeave;
    }
    public void display(){
        System.out.println("\nID: " + getFacultyId() + "\nName: " + getName() + "\nDepartment: " + getDepartment() + "\nDesignation: " + getDesignation() + "\nAddress: " + getAddress() + "\nGender: " + getGender());
        System.out.println("\nRemaining Leaves: \n1.Earned: " + el);
    }

    public void ResetLeave(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM");
        String formattedDate = myDateObj.format(myFormatObj);
        String resetDate = "01/01";
        if(resetDate == formattedDate) {
            el += 10;
        }
    }
}

public class Driver {
    public static void main(String[] args) {
        TeachingFaculty tf = new TeachingFaculty(1,"aditya", "cs", "HOD", "Tilakwadi","male");
        NonTeachingFaculty ntf = new NonTeachingFaculty(1,"sachin","cse","Non-Teaching","Shahapur","male");
        tf.grantCasualLeave(5);
        tf.grantEarnedLeave(1);
        tf.grantDutyLeave(3);
        ntf.grantEarnedLeave(5);
        tf.display();
        ntf.display();
    }
}