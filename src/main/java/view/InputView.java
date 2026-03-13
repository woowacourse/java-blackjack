package view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYERS_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String INPUT_BET_AMOUNT = "%s의 배팅 금액은?\n";

    private final Scanner scanner = new Scanner(System.in);

    public String inputPlayers() {
        System.out.println(INPUT_PLAYERS_MESSAGE);
        return scanner.nextLine();
    }

    public String inputHitOrStand(String name) {
        System.out.printf(INPUT_HIT_OR_STAND, name);
        return scanner.nextLine();
    }

    public String inputBetAmount(String name) {
        System.out.printf(INPUT_BET_AMOUNT, name);
        return scanner.nextLine();
    }
}
