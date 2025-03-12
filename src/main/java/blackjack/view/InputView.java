package blackjack.view;

import blackjack.gamer.Player;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_BETTING_MONEY = "%s의 배팅 금액은?";

    public String readNames() {
        return scanner.nextLine();
    }

    public void printErrorMessage(final Exception errorMessage) {
        System.out.println(errorMessage);
    }

    public String readBettingMoney(final Player player) {
        System.out.println(String.format(INPUT_BETTING_MONEY, player.getNickName()));
        return scanner.nextLine();
    }
}
