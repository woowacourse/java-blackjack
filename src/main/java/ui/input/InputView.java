package ui.input;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String RECEIVE_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner SCANNER = new Scanner(System.in);

    public String getPlayersName() {
        System.out.println(INPUT_PLAYERS_NAME);
        return SCANNER.next();
    }

    public String getPlayerInputGetMoreCard(final String playerName) {
        System.out.printf((RECEIVE_MORE_CARD) + "%n", playerName);
        return SCANNER.next();
    }

}
