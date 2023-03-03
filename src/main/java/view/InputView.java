package view;

import java.util.Scanner;

public class InputView {

    private static final String REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner SCANNER = new Scanner(System.in);

    public InputView() {
    }

    public String requestPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public String askMoreCard(String name) {
        System.out.printf("\n\n" + ASK_MORE_CARD_FORMAT + "\n", name);
        return SCANNER.nextLine();
    }

}
