
public class TestPayroll {
    

    public static void main(String[] args) {

        try {

            Payroll p1 = new Payroll();

            System.out.println("Initial size: " + p1.size());

            Employee e1 = new Employee();
            e1.name = "Alice";
            e1.ID = 1;
            e1.salary = 50000;

            Employee e2 = new Employee();
            e2.name = "Bob";
            e2.ID = 2;
            e2.salary = 60000;

            Employee e3 = new Employee();
            e3.name = "Charlie";
            e3.ID = 3;
            e3.salary = 70000;

            System.out.println("\nAdding employees...");
            p1.add_employee(e1);
            p1.add_employee(e2);
            p1.add_employee(e3);

            p1.print();
            System.out.println("Size after adding: " + p1.size());

            System.out.println("\nFinding Bob...");
            int index = p1.find_employee("Bob");
            System.out.println("Bob found at index: " + index);

            System.out.println("\nRemoving employee at index 1...");
            p1.remove_employee(1);
            p1.print();
            System.out.println("Size after removal: " + p1.size());

            try {
                System.out.println("\nTrying invalid removal...");
                p1.remove_employee(10);
            } catch (EmployeeIndexException ex) {
                System.out.println("Caught EmployeeIndexException correctly.");
            }

            try {
                System.out.println("\nTrying to find non-existent employee...");
                p1.find_employee("Zack");
            } catch (EmployeeNotFoundException ex) {
                System.out.println("Caught EmployeeNotFoundException correctly.");
            }

            System.out.println("\nTesting copy payroll...");
            Payroll p2 = new Payroll();
            p2.copy_payroll(p1);
            p2.print();

            System.out.println("\nTesting add payroll...");
            Payroll p3 = new Payroll();
            p3.add_employee(e1);
            p3.add_payroll(p1);
            p3.print();

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e);
        }
    }
}
