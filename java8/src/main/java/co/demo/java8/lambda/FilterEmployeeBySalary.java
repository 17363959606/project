package co.demo.java8.lambda;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee t) {
        return t.getSalary() >= 5000;
    }
}
