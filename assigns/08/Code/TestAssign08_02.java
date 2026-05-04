public class TestAssign08_02 {
    public static void main(String[] args) {

        Assign08_02<Integer> map = new Assign08_02<>();

        // Test isEmpty and size
        System.out.println("isEmpty (expect true): " + map.isEmpty());
        System.out.println("size (expect 0): " + map.size());

        // Test insert$opt and search$opt
        map.insert$opt("apple", 1);
        map.insert$opt("banana", 2);
        map.insert$opt("cherry", 3);
        System.out.println("size (expect 3): " + map.size());
        System.out.println("apple (expect 1): " + map.search$opt("apple"));
        System.out.println("banana (expect 2): " + map.search$opt("banana"));
        System.out.println("cherry (expect 3): " + map.search$opt("cherry"));
        System.out.println("missing (expect null): " + map.search$opt("missing"));

        // Test update existing key
        map.insert$opt("apple", 99);
        System.out.println("apple updated (expect 99): " + map.search$opt("apple"));
        System.out.println("size after update (expect 3): " + map.size());

        // Test insert$new
        map.insert$new("durian", 4);
        System.out.println("durian (expect 4): " + map.search$opt("durian"));
        System.out.println("size after insert$new (expect 4): " + map.size());

        // Test remove$opt
        Integer removed = map.remove$opt("banana");
        System.out.println("removed banana (expect 2): " + removed);
        System.out.println("banana after remove (expect null): " + map.search$opt("banana"));
        System.out.println("size after remove (expect 3): " + map.size());

        // Test remove missing key
        Integer removedMissing = map.remove$opt("missing");
        System.out.println("remove missing (expect null): " + removedMissing);

        // Test foritm
        System.out.println("All entries:");
        map.foritm((k, v) -> System.out.println("  " + k + " -> " + v));

        // Test isEmpty
        System.out.println("isEmpty (expect false): " + map.isEmpty());
    }
}