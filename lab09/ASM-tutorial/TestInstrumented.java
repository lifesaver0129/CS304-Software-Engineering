public class TestInstrumented
{
    public static void main(String[] args) {
        System.err.println("CALL printOne");
        printOne();
        System.err.println("RETURN printOne");

        System.err.println("CALL printOne");
        printOne();
        System.err.println("RETURN printOne");

        System.err.println("CALL printTwo");
        printTwo();
        System.err.println("RETURN printTwo");
    }
    
    public static void printOne() {
        System.err.println("CALL println");
        System.out.println("Hello World");
        System.err.println("RETURN println");
    }
    
    public static void printTwo() {
        System.err.println("CALL printOne");
        printOne();
        System.err.println("RETURN printOne");

        System.err.println("CALL printOne");
        printOne();
        System.err.println("RETURN printOne");
    }
}
