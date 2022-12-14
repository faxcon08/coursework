public class EmployeeBook {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void printError(String str) {System.out.println(ANSI_RED+str+ANSI_RESET);} ///// Вывод красным
    private Employee [] employees;

    public EmployeeBook(int amountEmployees) {  ///// Конструктор
        if (amountEmployees<1)
            throw new IllegalArgumentException("Количество сотрудников должно быть больше 0");

        employees = new Employee[amountEmployees];
    }
    public boolean addNewEmployee(String fullName, int department, int salary){
        Employee newEmployee = new Employee(fullName, department, salary);

        for (Employee employee : employees) { // проверяем на совпадение с именем
            if (employee != null && employee.equals(newEmployee)) {
                printError("Error: Сотрудника с таким же ФИО уже есть");
                return false;
            }
        }// for

        for (int i = 0; i<employees.length;i++) { //// Есть ли место в массиве ?
            if (employees[i] == null) {
                employees[i] = newEmployee;
                return true;
            }
        }// for

        printError("Error: Все места уже заняты. Невозможно добавить нового сотрудника");
        return  false;
        } // addNewEmployee

    public boolean deleteEmployee(String name) {
        if (!Employee.checkString(name)) {
            printError("Error: некорректное ФИО для удаления пустое");
            return false;
        }
        name=name.trim().replaceAll("\\s+"," ");
        for (int i = 0;i<employees.length;i++) {
            if (employees[i]!=null && employees[i].getFullName().equals(name)) {
                employees[i]=null;
                System.out.println("Сотрудник \""+name+"\" успешно удален");
                return true;
            }
        }// for
        System.out.println("Сотрудник \""+name+"\" не найден в системе для удаления");
        return false;
    }
    public boolean deleteEmployee(int id) {
        if (id < 0) {
            printError("Error: некорректное id для удаления сотрудника");
            return false;
        }
        for (int i =0; i<employees.length;i++) {
            if (employees[i]!=null && employees[i].getId() == id) {
                System.out.println("Сотрудник \""+employees[i].getFullName()+"\" успешно удален");
                employees[i]=null;
                return true;
            }
        }//for
        System.out.println("Сотрудник с id \"" + id + "\" не найден для удаления");
        return false;
    } // deleteEmployee

    public boolean changeEmployeeSalary(String name, int newSalary) {

        if (newSalary<0) {
            printError("Error: зарплата меньше нуля");
            return false;
        }
        if (!Employee.checkString(name)) {
            printError("Error: некорректное ФИО сотрудника для изменения зарплаты - пустое");
            return  false;
        }
        name = name.trim().replaceAll("\\s+", " ");
        int partName = name.split(" ").length;
        if (partName < 2) {
            printError("Error: некорректное ФИО сотрудника для изменения зарплаты - неполное ФИО");
            return  false;
        }

        for (Employee employee : employees) {
            if (employee!=null && employee.getFullName().equals(name)) {
                employee.setSalary(newSalary);
                return true;
            }
        }
        printError("Error: сотрудник \""+name+"\" не найден для изменения зарплаты");
        return false;
    }// changeEmployeeSalary
    public boolean changeEmployeeDepartment(String name, int department) {
        if (department<=0 || department>5) {
            printError("Error: Некорректный номер отдела \"" +department+"\" для изменения");
            return false;
        }
        Employee targetEmployee = this.findEmployee(name);
        if (targetEmployee != null) {
            targetEmployee.setDepartment(department);
            return true;
        }

        printError("Сотрудник \""+name+" \" не найден для изменения департамента ");
        return false;
    }

    private Employee findEmployee(String name) {  ////// поиск сотрудника по имени
        if (!Employee.checkString(name)) {
            return  null;
        }
        name = name.trim().replaceAll("\\s+", " ");
        for (int i =0;i<employees.length;i++) {
            if (employees[i] != null && employees[i].getFullName().equals(name)) {
                return employees[i];
            }
        }
        return  null;
    } // findEmployee

    @Override public String toString() {
        StringBuilder resultString = new StringBuilder("");
        String format = "%" + maxLengthInName() + "s";
        for (Employee employee : employees) {
            if (employee!=null) {
                resultString.append(String.format("id_%02d ---- Департамент %d ---- Сотрудник: "+format+" ---- Зарплата: %,10d",employee.getId(),
                employee.getDepartment(),employee.getFullName(),employee.getSalary())).append("\n");
            }
        }
        return resultString.toString();
    } // toString

    public int getTotalSalary() {
        int totalSalary = 0;
        for (Employee employee : employees) {
            if(employee!=null)
                totalSalary+=employee.getSalary();
        }
        return totalSalary;
    }

    public Employee findEmployeeWithMinSalary() { //// поиск сотрудника с Минимальной зп
        int minSalary = Integer.MAX_VALUE;
        Employee employeeWithMinSalary = null;
        for (Employee employee : employees) {
            if (employee!=null && employee.getSalary()<minSalary) {
                minSalary = employee.getSalary();
                employeeWithMinSalary=employee;
            }
        }//for
        return employeeWithMinSalary;
    }
    public Employee findEmployeeWithMaxSalary() { //// поиск сотрудника с МАКСИМАЛЬНОЙ зп
        int maxSalary = Integer.MIN_VALUE;
        Employee employeeWithMaxSalary = null;
        for (Employee employee : employees) {
            if (employee!=null && employee.getSalary()>maxSalary) {
                maxSalary = employee.getSalary();
                employeeWithMaxSalary=employee;
            }
        }//for
        return employeeWithMaxSalary;
    }

    public int getAverageSalary() {
        int averageSalary = this.getTotalSalary();
        int employeeAmount = 0;
        for (Employee employee : employees) {
            if(employee!=null)
                employeeAmount++;
        }
        if (employeeAmount==0)
            return 0;
        return averageSalary/employeeAmount;
    }

    public void printAllEmployees() {
        String s = "%"+maxLengthInName()+"s";
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.printf(s+"\n",employee.getFullName());
            }
        }
    }

    public void indexSalaryPerPercent(int percent) {
        if (percent < 0) {
            printError("Error: Процент идексации запрплаты не может быть отрицательным");
            return;
        }
        double rate = 1 + ((double)percent/100);
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary((int) (employee.getSalary()*rate));
            }
        }
    } // indexSalaryPerPercent
    private int maxLengthInName() { //// Максимальная длинна строки с именем
        int lenght = 1;
        for (Employee employee : employees) {
            if (employee!=null && employee.getFullName().length()>lenght) {
                lenght=employee.getFullName().length();
            }
        }
        return lenght;
    }

    public void findAllEmployeesWithLowerSalary(int salary) {  ////////// сотрудники с меньшей зп, чем
        if (salary < 0) {
            printError("Error: зарплата не может быть отрицательной");
            return;
        }
        System.out.println("Сотрудники с зарпалатой меньше, чем " + salary+" :");
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < salary) {
                System.out.println(employee);
            }
        }
    }// findALlEmployeesWithLowerSalary

    public void findAllEmployeesWithHigherSalary(int salary) { /////// сотрудники с большей зп, чем
        if (salary < 0) {
            printError("Error: зарплата не может быть отрицательной");
            return;
        }
        System.out.println("Сотрудники с зарпалатой большей, чем " + salary+" :");
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() > salary) {
                System.out.println(employee);
            }
        }
    } ////// findAllEmployeesWithHigherSalary
    public Employee findEmployeeWithMinSalaryInDepartment(int department) { //// поиск сотрудника с Минимальной зп
        int minSalary = Integer.MAX_VALUE;
        Employee employeeWithMinSalary = null;

        if (department<1 || department>5) {
            printError("Некорректный номер департамента");
            return employeeWithMinSalary;
        }

        for (Employee employee : employees) {
            if (employee!=null && employee.getDepartment()==department && employee.getSalary()<minSalary) {
                minSalary = employee.getSalary();
                employeeWithMinSalary=employee;
            }
        }//for
        return employeeWithMinSalary;
    } //// findEmployeeWithMinSalaryInDepartment
    public Employee findEmployeeWithMaxSalaryInDepartment(int department) { //// поиск сотрудника с МАКСИМАЛЬНОЙ зп
        int maxSalary = Integer.MIN_VALUE;
        Employee employeeWithMaxSalary = null;

        if (department<1 || department>5) {
            printError("Некорректный номер департамента");
            return employeeWithMaxSalary;
        }

        for (Employee employee : employees) {
            if ((employee!=null && employee.getDepartment()==department) && employee.getSalary()>maxSalary) {
                maxSalary = employee.getSalary();
                employeeWithMaxSalary=employee;
            }
        }//for
        return employeeWithMaxSalary;
    } //// findEmployeeWithMaxSalaryInDepartment
} //  EmployeeBook

