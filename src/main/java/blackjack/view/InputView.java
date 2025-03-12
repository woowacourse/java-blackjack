package blackjack.view;

import blackjack.gamer.Player;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PLAYERS_NICKNAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String INPUT_BETTING_MONEY = "%s의 배팅 금액은?";

    public String readNames() {
        System.out.println(INPUT_PLAYERS_NICKNAMES);
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
