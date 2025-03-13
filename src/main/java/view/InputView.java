package view;

import static error.ErrorMessage.INPUT_ONLY_Y_OR_N;
import static error.ErrorMessage.IS_BLANK;
import static error.ErrorMessage.ONLY_PERMIT_NUMBER;

import domain.participant.Participant;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_USER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String EXTRA_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String BETTING_AMOUNT_INPUT_MESSAGE = "%s의 배팅 금액은?";
    private static final String CHECK_NUMBER_REGEX = "\\d+";

    public int inputBettingAmountOfPlayer(String playerName) {
        System.out.println(BETTING_AMOUNT_INPUT_MESSAGE.formatted(playerName));
        String userInput = scanner.nextLine();
        validateNumber(userInput);
        return Integer.parseInt(userInput);
    }

    private final Scanner scanner = new Scanner(System.in);

    public String inputUserNames() {
        System.out.println(INPUT_USER_NAME_MESSAGE);
        String userInput = scanner.nextLine();
        validateIsNotBlank(userInput);
        return userInput;
    }

    public String inputPlayerWantMoreCard(Participant participant) {
        System.out.println(EXTRA_CARD_MESSAGE.formatted(participant.getName()));
        String userInput = scanner.nextLine();
        validateIsYOrN(userInput);
        return userInput;
    }

    private void validateIsNotBlank(String userInput) {
        if (userInput.isBlank()) {
            throw new IllegalArgumentException(IS_BLANK.getMessage());
        }
    }

    private void validateIsYOrN(String userInput) {
        if (userInput.equalsIgnoreCase("n") ||
            userInput.equalsIgnoreCase("y")) {
            return;
        }
        throw new IllegalArgumentException(INPUT_ONLY_Y_OR_N.getMessage());
    }

    private void validateNumber(String userInput) {
        if (!userInput.matches(CHECK_NUMBER_REGEX)) {
            throw new IllegalArgumentException(ONLY_PERMIT_NUMBER.getMessage());
        }
    }
}
