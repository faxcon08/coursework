public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void printMessage(String str) {
        System.out.println(ANSI_RED+str+ANSI_RESET);
    }
    public static void main(String[] args) {

        EmployeeBook myEmployeeBook = new EmployeeBook(10);
        myEmployeeBook.addNewEmployee("Иванов Иван Иванович",1,1_000);
        myEmployeeBook.addNewEmployee("Иванов Георгий Иванович",1,500);
        myEmployeeBook.addNewEmployee("Петров Иван Иванович",1,1_000);
        myEmployeeBook.addNewEmployee("Сидоров Иван Иванович",2,2_000);
        myEmployeeBook.addNewEmployee("Васильев Иван Иванович",3,2_000);
        myEmployeeBook.deleteEmployee("Васильев Иван Иванович");

        myEmployeeBook.addNewEmployee("Васильевич Сергей Иванович",3,1_000);

        System.out.println(myEmployeeBook);
        myEmployeeBook.changeEmployeeDepartment("Иванов Иван Иванович", 4);
        myEmployeeBook.changeEmployeeSalary("          Сидоров Иван     Иванович", 20_000);
        System.out.println(myEmployeeBook);
    }// main
}// Main