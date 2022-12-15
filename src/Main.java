public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void printMessage(String str) {
        System.out.println(ANSI_CYAN+str+ANSI_RESET);
    }
    public static void main(String[] args) {

        int employeeAmount = 10;

        EmployeeBook myEmployeeBook = new EmployeeBook(employeeAmount);
        myEmployeeBook.addNewEmployee("Иванов Иван Иванович",1,1_000);
        myEmployeeBook.addNewEmployee("Иванов Георгий Иванович",1,500);
        myEmployeeBook.addNewEmployee("Петров Иван Иванович",1,1_000);
        myEmployeeBook.addNewEmployee("Сидоров Иван Иванович",2,2_000);
        myEmployeeBook.addNewEmployee("Васильев Иван Иванович",3,2_000);
        myEmployeeBook.addNewEmployee("Лебедев Павел Иванович", 1, 1500);
        myEmployeeBook.addNewEmployee("Васильевич Сергей Иванович",3,1_000);
        myEmployeeBook.addNewEmployee("Павлов Сергей Сергеевич", 4, 2500);
        myEmployeeBook.addNewEmployee("Павлов Иван Сергеевич", 3, 3500);
        printMessage("Попытка добавить лишних сотрудников и сотрудника с такой же ФИО");
        myEmployeeBook.addNewEmployee("Ульянов Иван Дмитривевич", 3, 1500);
        myEmployeeBook.addNewEmployee("Каменев Сергей Сереевич", 1, 1_500);
        myEmployeeBook.addNewEmployee("Васильев Сергей Павлович", 1, 2_500);
        myEmployeeBook.addNewEmployee("Петров Иван Иванович",1,1_200);

        String nameTODelete = "Васильев Иван Иванович";
        int idToDelete = 6;
        printMessage("Список сотрудников после добавления");
        System.out.println(myEmployeeBook);
        myEmployeeBook.deleteEmployee(nameTODelete);
        myEmployeeBook.deleteEmployee(idToDelete);
        printMessage("Список сотрудников после удалениния сотрудниов с ФИО - "+nameTODelete+" и id "+idToDelete);
        System.out.println(myEmployeeBook);
        printMessage("Сумма затрат на Зарплаты: "+ myEmployeeBook.getTotalSalary()+ " ---- Среднее значение заплаты: "+myEmployeeBook.getAverageSalary());
        Employee employee = myEmployeeBook.findEmployeeWithMinSalary();
        printMessage("Сотрудник с минимальной Зарплатой: "+employee.getFullName()+ " - "+employee.getSalary());
        employee = myEmployeeBook.findEmployeeWithMaxSalary();
        printMessage("Сотрудник с МАКСИМАЛЬНОЙ Зарплатой: "+employee.getFullName()+ " - "+employee.getSalary());
        printMessage("\n Список ФИО всех действующих сотрудников:");
        myEmployeeBook.printAllEmployees();

        int percent = 10;
        printMessage("Проиндексируем ЗП всех сотрудников на "+percent+"%");
        myEmployeeBook.indexSalaryPerPercent(percent);
        printMessage("Результат: ");
        System.out.println(myEmployeeBook);

        int department = 1;
        employee = myEmployeeBook.findEmployeeWithMinSalaryInDepartment(department);
        printMessage("Сотрудник с минимальной  ЗП в отделе #"+department+" :"+employee.getFullName()+" ---- "+employee.getSalary());
        employee = myEmployeeBook.findEmployeeWithMaxSalaryInDepartment(department);
        printMessage("Сотрудник с МАКСИМАЛЬНОЙ ЗП в отделе #"+department+" :"+employee.getFullName()+" ---- "+employee.getSalary());
        printMessage("Общие затраты  по отделу  #"+department+" на ЗП - "+myEmployeeBook.getTotalSalaryInDepartment(department));
        printMessage("Средняя ЗП по отделу #"+department+" - "+myEmployeeBook.getAverageSalaryInDepartment(department));
        percent = 20;
        printMessage("Индексируем ЗП отдела #"+department+" на "+percent+"%.");
        myEmployeeBook.indexSalaryPerPercentInDepartment(department,percent);
        myEmployeeBook.printAllEmployeesInDepartment(department);

        int salary = 2000;
        printMessage("Сотрудники c ЗП меньше, чем "+salary+" :");
        myEmployeeBook.findAllEmployeesWithLowerSalary(salary);
        printMessage("Сотрудники c ЗП БОЛЬШЕЙ, чем "+salary+" :");
        myEmployeeBook.findAllEmployeesWithHigherSalary(salary);

        String employeeName = "  Иванов    Иван    Иванович    ";
        int newId = 4;
        printMessage("Изменяем ЗП сотрудника: "+employeeName+" на занчение - "+salary);
        myEmployeeBook.changeEmployeeSalary(employeeName, salary);

        printMessage("Меняем департамент на "+newId+" для сотрудника с ФИО: "+employeeName);
        myEmployeeBook.changeEmployeeDepartment(employeeName, newId);

        myEmployeeBook.printAllEmployees();

    }// main
}// Main