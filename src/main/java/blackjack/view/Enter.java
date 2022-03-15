package blackjack.view;

import java.util.Scanner;

public class Enter implements Enterable {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String enter() {
        return scanner.nextLine();
    }
}
