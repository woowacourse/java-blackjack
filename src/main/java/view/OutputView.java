package view;

import java.util.List;

public class OutputView {

    private static final String lineSeparator = System.lineSeparator();

    public void println(String message) {
        System.out.println(message);
    }

    public void printfList(String outputMessage, String value) {
        System.out.printf(outputMessage, value);
    }

    public void separatorLine() {
        System.out.print(lineSeparator);
    }

    public void printList(List<String> values) {
        values.forEach(this::println);
        separatorLine();
    }
}
