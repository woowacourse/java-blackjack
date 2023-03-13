package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_MORE_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String REQUEST_BETTING_AMOUNT_ERROR_MESSAGE = "배팅 금액은 정수만 입력 가능합니다.";
    private static final String REQUEST_BETTING_AMOUNT = "%s의 배팅 금액은?";
    private static final String DELIMITER = ",";
    private static final int LIMIT_REMOVED = -1;

    private final Scanner Scanner;

    public InputView() {
        Scanner = new Scanner(System.in);
    }

    public List<String> requestPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME);
        return convertToList(Scanner.nextLine());
    }

    private List<String> convertToList(String input) {
        return Arrays.stream(input.split(DELIMITER, LIMIT_REMOVED))
                .collect(Collectors.toList());
    }

    public Answer askMoreCard(String name) {
        System.out.printf("\n\n" + ASK_MORE_CARD_FORMAT + "\n", name);
        return Answer.from(Scanner.nextLine());
    }

    public int requestBettingAmount(String name) {
        System.out.printf("\n" + REQUEST_BETTING_AMOUNT + "\n", name);

        try {
            return Integer.parseInt(Scanner.nextLine());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(REQUEST_BETTING_AMOUNT_ERROR_MESSAGE);
        }
    }

}
