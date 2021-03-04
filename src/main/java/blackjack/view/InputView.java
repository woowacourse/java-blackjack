package blackjack.view;

import blackjack.domain.participant.Player;
import java.util.Scanner;

public class InputView {

    private static final String YES = "y";
    private static final String NO = "n";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    public static boolean wantsReceive(Player player) {
        askShouldDrawAnotherCard(player.getName());

        //TODO n 이외의 값이 와도 false? n 이외의 값이면 어떻게 처리할지 고민
        String input = SCANNER.nextLine();
        return YES.equals(input);
    }

    private static void askShouldDrawAnotherCard(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)\n", playerName, YES, NO);
    }
}
