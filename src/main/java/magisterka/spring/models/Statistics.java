package magisterka.spring.models;

public class Statistics {
    public String role;
    public int count;
    public double averageSalary;
    public UniversalPerson minSalaryPerson;
    public UniversalPerson maxSalaryPerson;

    public Statistics() {
    }

    public Statistics(String role, int count, double averageSalary,
                      UniversalPerson minSalaryPerson, UniversalPerson maxSalaryPerson) {
        this.role = role;
        this.count = count;
        this.averageSalary = averageSalary;
        this.minSalaryPerson = minSalaryPerson;
        this.maxSalaryPerson = maxSalaryPerson;
    }
}
