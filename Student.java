package model;

 public class Student {
    private String roll;
    private String name;
    private int marks;

    public Student(String roll, String name, int marks) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
    }

    public String getRoll() { return roll; }
    public String getName() { return name; }
    public int getMarks() { return marks; }

    @Override
    public String toString() {
        return roll + "," + name + "," + marks;
    }

    public static Student fromString(String data) {
        String[] arr = data.split(",");
        return new Student(arr[0], arr[1], Integer.parseInt(arr[2]));
    }
}
