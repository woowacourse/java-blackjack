package console;

import java.util.Scanner;
import view.View;

public abstract class Console {
    private final Scanner scanner = new Scanner(System.in);

    protected void display(View view) {
        System.out.print(view.display());
    }

    protected String readLine() {
        return scanner.nextLine();
    }
}
