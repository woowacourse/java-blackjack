package view;

import domain.Player;
import view.mesage.InputMessage;

import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public String askUsersName() {
        System.out.println(InputMessage.ASK_USER_NAME.getMessage());
        return scanner.nextLine();
    }

    public String askAddCard(String name) {
        System.out.printf(InputMessage.ASK_ADD_CARD.getMessage(), name);
        System.out.println();
        return scanner.nextLine();
    }

    public String askBetAmount(Player player) {
        System.out.printf("%s의 배팅 금액은?", player.getName());
        System.out.println();
        return scanner.nextLine();
    }
}
