package view;

import java.util.Scanner;

public class InputReader {

    private Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

}
