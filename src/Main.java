public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void printMessage(String str) {
        System.out.println(ANSI_RED+str+ANSI_RESET);
    }
    public static void main(String[] args) {

    }
}