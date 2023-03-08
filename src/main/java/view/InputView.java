package view;

import java.util.Scanner;

public class InputView {

    private static final String REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String REQUEST_BETTING_MONEY_ERROR_MESSAGE = "배팅 금액은 정수만 입력 가능합니다.";
    private static final String REQUEST_BETTING_MONEY = "%s의 배팅 금액은?";

    private final Scanner Scanner;

    public InputView() {
        Scanner = new Scanner(System.in);
    }

    public String requestPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME);
        return Scanner.nextLine();
    }

    public String askMoreCard(String name) {
        System.out.printf("\n\n" + ASK_MORE_CARD_FORMAT + "\n", name);
        return Scanner.nextLine();
    }

    public int requestBettingMoney(String name) {
        System.out.printf("\n" + REQUEST_BETTING_MONEY + "\n", name);

        try {
            return Integer.parseInt(Scanner.nextLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(REQUEST_BETTING_MONEY_ERROR_MESSAGE);
        }
    }

}
