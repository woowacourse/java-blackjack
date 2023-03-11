package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String DELIMITER_WITH_BLANK = "\\s*,\\s*";
    private static final String YES_ANSWER_ABOUT_ONE_MORE_CARD = "y";
    private static final String NO_ANSWER_ABOUT_ONE_MORE_CARD = "n";
    private static final int MIN_BETTING_MONEY = 0;

    private final Scanner scanner = new Scanner(System.in);

    public List<String> receivePlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = getInput();

        return Arrays.stream(input.split(DELIMITER_WITH_BLANK))
                .collect(Collectors.toList());
    }

    public int receiveBettingMoney(final String playerName) {
        System.out.println("\n" + playerName + "의 배팅 금액은?");
        String input = getInput();

        isBlank(input);
        validateDigit(input);
        validatePositive(Integer.parseInt(input));

        return Integer.parseInt(input);
    }

    public Boolean askReceiveMoreCard(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 " + YES_ANSWER_ABOUT_ONE_MORE_CARD
                + ", 아니오는 " + NO_ANSWER_ABOUT_ONE_MORE_CARD + ")");
        String input = getInput();

        isBlank(input);
        validateCorrectResponse(input);

        return input.equals(YES_ANSWER_ABOUT_ONE_MORE_CARD);
    }

    private String getInput() {
        return scanner.nextLine();
    }

    private void isBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력할 수 없습니다.");
        }
    }

    private void validateCorrectResponse(final String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException(
                    "[ERROR] 예는 " + YES_ANSWER_ABOUT_ONE_MORE_CARD
                            + ", 아니오는 " + NO_ANSWER_ABOUT_ONE_MORE_CARD + "을 입력해주세요.");
        }
    }

    private void validateDigit(final String input) {
        boolean isDigit = input.chars()
                .allMatch(Character::isDigit);

        if (!isDigit) {
            throw new IllegalArgumentException("[ERROR] 정수만 입력 가능합니다.");
        }
    }

    private void validatePositive(final int input) {
        if (input < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 " + MIN_BETTING_MONEY + "원 이상이여야 합니다.");
        }
    }
}
