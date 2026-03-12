package view;

public class OutputView {

    private static final String lineSeparator = System.lineSeparator();

    public void println(String outputMessage) {
        System.out.println(outputMessage);
    }

    public void printfList(String outputMessage, String value) {
        System.out.printf(outputMessage, value);
    }

    public void printRepeated(String outputMessage, int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            println(outputMessage);
        }
    }

    public void separatorLine() {
        System.out.print(lineSeparator);
    }
}
