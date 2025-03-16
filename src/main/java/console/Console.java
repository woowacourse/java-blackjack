package console;

import java.util.Scanner;

public abstract class Console {
    private final Scanner scanner = new Scanner(System.in);

    protected void display(String view) {
        System.out.print(view);
    }

    protected String readLine() {
        return scanner.nextLine();
    }
}
