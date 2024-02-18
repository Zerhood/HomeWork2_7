import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeBook {
    Map<String, Employee> employees;

    public EmployeeBook() {
        employees = new HashMap<>();
    }

    public void addEmployee(Employee emp) {
        employees.put(emp.getName(), emp);
    }

    public void deleteEmployee(int id) {
        employees.entrySet().removeIf(s -> s.getValue().getId() == id);
    }

    public void deleteEmployee(String name) {
        employees.remove(name);
    }

    public void setEmployee(String name, int salary, int department) {
        for (Employee e : employees.values()) {
            if (e.getName().equals(name)) {
                if (salary > 0 && department > 0 && department <= 5) {
                    e.setSalary(salary);
                    e.setDepartment(department);
                    System.out.println("Сотрудник - " + e.getName() + " изменён.");
                } else {
                    System.out.println("Ошибка изменения данных!");
                }
            } else {
                System.out.println("Такой сотрудник не найден!");
                return;
            }
        }
    }

    public void getAllEmpByDepartment() {
        employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + "й отдел, сотрудники: " + v.toString().replaceAll("[]\\[]", "")));
    }

    public void getAllEmployee() {
        employees.values().forEach(System.out::println);
    }

    public void getSumSalariesPerMonth() {
        System.out.println("Сумма затрат на зарплату за месяц - " + employees.values().stream()
                .mapToInt(Employee::getSalary)
                .sum());
    }

    public void getEmpMinSalaryInDepartment() {
        System.out.println("Минимальная зарплата - " + employees.values().stream()
                .mapToInt(Employee::getSalary)
                .min());
    }

    public void getEmpMaxSalaryInDepartment() {
        System.out.println("Максимальная зарплата - " + employees.values().stream()
                .mapToInt(Employee::getSalary)
                .max());
    }

    public void getAverageSalary() {
        System.out.println("Средняя зарплата - " + employees.values().stream()
                .mapToInt(Employee::getSalary)
                .average());
    }

    public void getNameEmployees() {
        employees.values().forEach(s -> System.out.println(s.getName()));
    }

    public void setIndexingSalary(int percent) {
        double newPercent = (double) percent / 100 + 1;
        employees.values().forEach(e -> e.setSalary((int) (e.getSalary() * newPercent)));
    }

    public void getEmpMinSalaryInDepartment(int department) {
        System.out.println("Сотрудник " + department + " отдела с минимальной зарплатой - " +
                employees.values().stream()
                        .filter(s -> s.getDepartment() == department)
                        .mapToInt(Employee::getSalary)
                        .min());
    }

    public void getEmpMaxSalaryInDepartment(int department) {
        System.out.println("Сотрудник " + department + " отдела с максимальной зарплатой - " +
                employees.values().stream()
                        .filter(s -> s.getDepartment() == department)
                        .mapToInt(Employee::getSalary)
                        .max());
    }

    public void getSumSalariesPerMonthInDepartment(int department) {
        System.out.println("Сумма затрат на зарплату " + department + " отдела составила - " +
                employees.values().stream()
                        .filter(s -> s.getDepartment() == department)
                        .mapToInt(Employee::getSalary)
                        .sum());
    }

    public void getAverageSalaryInDepartment(int department) {
        System.out.println("Средняя зарплата по " + department + " отделу составила " +
                employees.values().stream()
                        .filter(s -> s.getDepartment() == department)
                        .mapToInt(Employee::getSalary)
                        .average());
    }

    public void setIndexingSalaryInDepartment(int percent, int department) {
        double newPercent = (double) percent / 100 + 1;
        employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .forEach(e -> e.setSalary((int) (e.getSalary() * newPercent)));
    }

    public void getNameEmployeesInDepartment(int department) {
        employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary()));
    }

    public void getAllEmpLessSpecNumber(int number) {
        employees.values().stream()
                .filter(e -> e.getSalary() < number)
                .forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary()));
    }

    public void getAllEmpGreatSpecNumber(int number) {
        employees.values().stream()
                .filter(e -> e.getSalary() >= number)
                .forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getSalary()));
    }
}