package ammonclegg.java.apprentice.overloading;

/**
 * @author ammonclegg on 10/12/18.
 */
public class Person {
  protected String name;
  protected int age;

  public Person(int age, String name) {
    this.age = age;
    this.name = name;
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
}
