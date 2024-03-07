package view;

public class OutputView {

    private OutputView() {
    }

    public static void printNewLine() {
        System.out.print(System.lineSeparator());
    }

    public static void print(String toBePrint) {
        System.out.println(toBePrint);
    }

}
