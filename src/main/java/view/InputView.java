package view;

import domain.Money;

import java.util.Scanner;

public class InputView {
    private static final String READ_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String READ_ADD_CARD_COMMAND_MESSAGE =
            "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator();
    private static final String READ_BETTING_MONEY = "%s의 배팅 금액은?" + System.lineSeparator();
    private static final Scanner scanner = new Scanner(System.in);

    public String readPlayerNames() {
        System.out.println(READ_PLAYER_NAMES_MESSAGE);
        return scanner.nextLine();
    }

    public String readCommand(String name) {
        System.out.println();
        System.out.printf(READ_ADD_CARD_COMMAND_MESSAGE, name);
        return scanner.nextLine();
    }

    public int readBettingMoney(String name) {
        System.out.println();
        System.out.printf(READ_BETTING_MONEY, name);
        return Integer.valueOf(scanner.nextLine());
    }

}
