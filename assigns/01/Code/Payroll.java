
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    private Employee[] people;
    private int maximum_size;
    private int current_size;

    // default constructor
    public Payroll() {
        int init = INITIAL_MAXIMUM_SIZE;
        if (init <= 0) init = 1;

        this.people = new Employee[init];
        this.maximum_size = init;
        this.current_size = 0;
    }

    // return current_size
    public int size() {
        return current_size;
    }

    // print employees, one per line
    public void print() {
        for (int i = 0; i < current_size; i++) {
            System.out.println(people[i]);
        }
    }

    // add a new employee, grow if needed
    public void add_employee(Employee newbie) {
        if (newbie == null) {
            return;
        }

        if (current_size >= maximum_size) {
            double_size();
        }

        people[current_size] = newbie;
        current_size++;
    }

    // remove employee at index i (order not preserved)
    public void remove_employee(int i) throws EmployeeIndexException {
        if (i < 0 || i >= current_size) {
            throw new EmployeeIndexException();
        }

        int last = current_size - 1;
        people[i] = people[last];
        people[last] = null;
        current_size--;
    }

    // find first employee whose name equals target_name
    public int find_employee(String target_name) throws EmployeeNotFoundException {
        for (int i = 0; i < current_size; i++) {
            if (people[i] != null && people[i].name != null && people[i].name.equals(target_name)) {
                return i;
            }
        }
        throw new EmployeeNotFoundException();
    }

    // assign one payroll to another (copy structure, keep same Employee references)
    public void copy_payroll(Payroll source) {
        if (source == null) {
            people = new Employee[INITIAL_MAXIMUM_SIZE];

            maximum_size = people.length;
            current_size = 0;
            return;
        }

        this.maximum_size = source.maximum_size;
        if (this.maximum_size <= 0) this.maximum_size = 1;

        this.people = new Employee[this.maximum_size];
        this.current_size = source.current_size;

        for (int i = 0; i < this.current_size; i++) {
            this.people[i] = source.people[i];
        }
    }

    // combine two payrolls into one (allow duplicates)
    public void add_payroll(Payroll source) {
        if (source == null) return;

        for (int i = 0; i < source.current_size; i++) {
            add_employee(source.people[i]);
        }
    }

    // private helper: double array capacity
    private void double_size() {
        int new_max = maximum_size * 2;
        if (new_max <= maximum_size) {
            new_max = maximum_size + 1;
        }

        Employee[] bigger = new Employee[new_max];
        for (int i = 0; i < current_size; i++) {
            bigger[i] = people[i];
        }

        people = bigger;
        maximum_size = new_max;
    }
}

