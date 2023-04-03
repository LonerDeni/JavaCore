package ReadJson;

public class EmployeeRead {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public EmployeeRead() {
        // Пустой конструктор
    }

    public EmployeeRead(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}