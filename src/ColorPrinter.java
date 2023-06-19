public  class ColorPrinter {
    // ANSI escape code for resetting text color
    public static final String RESET = "\u001B[0m";

    // ANSI escape codes for different colors
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void printRedText(String text) {
        System.out.print(RED + text + RESET);
    }

    public static void printGreenText(String text) {
        System.out.print(GREEN + text + RESET);
    }

    public static void printYellowText(String text) {
        System.out.print(YELLOW + text + RESET);
    }

    public static void printlnRedText(String text) {
        System.out.println(RED + text + RESET);
    }

    public static void printlnGreenText(String text) {
        System.out.println(GREEN + text + RESET);
    }

    public static void printlnYellowText(String text) {
        System.out.println(YELLOW + text + RESET);
    }


}
