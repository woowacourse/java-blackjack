package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BETTING_MONEY_INPUT_MESSAGE = "의 배팅 금액은?";
    private static final String CARD_INPUT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String YES_INPUT_MESSAGE = "y";
    private static final String NO_INPUT_MESSAGE = "n";

    private static final String DELIMITER = ",";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readNames() {
        printMessage(NAME_INPUT_MESSAGE);
        String names = input();
        return Arrays.stream(names.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String readIntention(String name) {
        printMessage(name + CARD_INPUT_MESSAGE);
        try {
            String intention = input().toLowerCase();
            validateIntention(intention);
            return intention;
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
            return readIntention(name);
        }
    }

    private void validateIntention(String intention) {
        if (!intention.equals(YES_INPUT_MESSAGE) && !intention.equals(NO_INPUT_MESSAGE)) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
    }

    private String input() {
        String inputValue = scanner.nextLine();
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        return inputValue;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    //TODO: 검증 다시 생각해보기
    public double readBettingMoney(String name) {
        printMessage(name + BETTING_MONEY_INPUT_MESSAGE);
        try {
            String input = input();
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            printMessage("숫자 타입만 입력 가능합니다.");
            return readBettingMoney(name);
        }
    }
}
