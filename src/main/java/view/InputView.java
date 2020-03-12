package view;

import domain.gamer.Player;

import java.util.Scanner;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);

    public static String inputAsPlayerName() {
        OutputView.printPlayerNamesGuide();
        return scanner.nextLine();
    }

    public static String inputAsDrawable(Player player) {
        OutputView.printAddCardGuide(player);
        return scanner.next();
    }

}
