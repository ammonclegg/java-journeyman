package ammonclegg.java.apprentice.overloading;

import java.util.Objects;

/**
 * @author ammonclegg on 8/17/18.
 */
public class Student {

  private String id;
  private String name;
  private int age;
  private double gpa;

  public Student(String id, String name, int age, double gpa) {
    this.id = id;
    this.name = name;
    this.age = age;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
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
