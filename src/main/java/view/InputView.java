package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String NUMERIC = "^[0-9]*$";

    public List<String> requestPlayerNames() {
        System.out.println(Message.PLAYER_NAMES_INPUT.message);
        String playerNames = SCANNER.nextLine();

        return Arrays.stream(playerNames.split(DELIMITER))
                .collect(Collectors.toList());
    }

    public String requestMoreCard(String name) {
        String format = String.format(Message.PLAYER_WANT_MORE_CARD.message, name);
        System.out.println(format);

        return SCANNER.nextLine();
    }

    public int requestBetAmount(String name) {
        String format = String.format(Message.PLAYER_BET_AMOUNT.message, name);
        System.out.println(format);

        return readBetAmount();
    }

    private int readBetAmount() {
        String input = SCANNER.nextLine();

        if (!input.matches(NUMERIC)) {
            throw new IllegalArgumentException(Message.NOT_NUMERIC_ERROR.message);
        }

        return Integer.parseInt(input);
    }

    private enum Message {
        PLAYER_NAMES_INPUT("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"),
        PLAYER_WANT_MORE_CARD("%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오 n)"),
        PLAYER_BET_AMOUNT("%s의 배팅 금액은?"),
        NOT_NUMERIC_ERROR("배팅 금액은 숫자만 입력해주세요");


        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
