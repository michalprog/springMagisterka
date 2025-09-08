package magisterka.spring.models;

public class UniversalPerson {
    public String name;
    public String surname;
    public int salary;
    public int role;

    public UniversalPerson() {
    }

    public UniversalPerson(String name, String surname, int salary, int role) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.role = role;
    }
}