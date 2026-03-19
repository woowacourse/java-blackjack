package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final Scanner scanner = new Scanner(System.in);

    public static String askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        return scanner.nextLine();
    }

    public static String askHitOrStand(String playerName) {
        System.out.printf(ASK_HIT_OR_STAND, playerName);
        return scanner.nextLine();
    }

    public static String askPlayerBettingAmount(String playerName) {
        System.out.printf("%n%s의 배팅 금액은?%n", playerName);
        return scanner.nextLine();
    }
}
