public class Employee {
    private static int count = 0; // Счетчик сотрудников
    private int id;
    private final String fullName; // ФИО Сотрудника
    private int department; // Отдел 0-5
    private int salary; // Зарплата

    public static boolean checkString(String str) { // примитивная проверка строки
        if ((str == null) || str.isBlank()) {
            return false;
        }
        return true;
    }

    public Employee(String fullName, int department, int salary) { // Конструктор
        id = count++;


        if (checkString(fullName)) {
            fullName = fullName.trim().replaceAll("\\s+", " "); // убираем лишние пробелы
            if (fullName.split(" ").length>=2)
                this.fullName = fullName;
            else throw new IllegalArgumentException("Некорректное имя работника");
        } else {
            throw new IllegalArgumentException("ФИО пустое");
        }

        if (department > 0 && department <= 5) this.department = department;
        else throw new IllegalArgumentException("Некорректный номер отдела");

        if (salary >= 0) this.salary = salary;
        else throw new IllegalArgumentException("Некорректная зарплата");
    }// Constructor


    ///////////////// Геттеры ///////////////////////////
    public String getFullName() {return fullName;}
    public int getDepartment() {return department;}
    public int getSalary() {return salary;}
    public int getId() {return id;}

    //////////////////// Сеттеры ///////////////////////
    public void setDepartment(int department) {
        if (department >= 0 && department <= 5) this.department = department;
        else throw new IllegalArgumentException("Некорректный номер отдела");
    }

    public void setSalary(int salary) {
        if (salary >= 0) this.salary = salary;
        else throw new IllegalArgumentException("Некорректная зарплата");
    }
   ////////////////////////////////////////////////
    @Override
    public String toString() {
        return String.format("id_%02d ---- Департамент %d Сотрудник: %30s --- Зарпалта:%,8d",id,department,fullName,salary);
    }
    @Override
    public boolean equals(Object c) {    /////////////////////////////// сравниваем лишь по ФИО
        if (c==null || c.getClass()!=this.getClass()) return  false;
        Employee employeeToCompare = (Employee) c;
        return this.fullName.equals(employeeToCompare.fullName);
    }

}
