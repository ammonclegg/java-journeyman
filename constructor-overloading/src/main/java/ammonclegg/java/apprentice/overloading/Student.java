package ammonclegg.java.apprentice.overloading;

import java.util.Objects;

/**
 * @author ammonclegg on 8/17/18.
 */
public class Student extends Person {

  private String id;
  private double gpa;

  public Student(String id, String name, int age, double gpa) {
    super(age, name);
    this.id = id;
    this.gpa = gpa;
  }

  public Student(String id, String name, int age) {
    this(id, name, age, 2.0);
  }

  public Student(String id, String name) {
    this(id, name, 21);
  }

  public Student(String id) {
    this(id, "Joe Friswald");
  }

  public double getGpa() {
    return gpa;
  }

  public void setGpa(double gpa) {
    this.gpa = gpa;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return age == student.age &&
        Double.compare(student.gpa, gpa) == 0 &&
        Objects.equals(id, student.id) &&
        Objects.equals(name, student.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, age, gpa);
  }

  @Override
  public String toString() {
    return "Student{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", gpa=" + gpa +
        '}';
  }
}
