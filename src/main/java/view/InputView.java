package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final String PLAYER_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String COMMA = ",";
    private static final String GET_MORE_CARD_OR_NOT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String GET_CARD_OR_NOT_REGEX = "^[y,n]$";
    private static final String YES = "y";
    private static final String BETTING_MONEY_INPUT_MESSAGE = "%s의 베팅 금액은?";

    private static final String EMPTY_INPUT_ERROR_MESSAGE = "[ERROR] 빈 값을 입력할 수 없습니다.";
    private static final String INPUT_ONLY_Y_OR_N_ERROR = "[ERROR] y나 n만 입력할 수 있습니다.";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    public List<String> inputPlayerName() {
        System.out.println(PLAYER_NAME_INPUT_MESSAGE);
        return splitByComma(input());
    }

    private List<String> splitByComma(String names) {
        return Arrays.asList(names.split(COMMA));
    }

    public List<String> inputBettings(List<String> playerNames) {
        List<String> bettingMoneys = new ArrayList<>();
        for (String playerName : playerNames) {
            bettingMoneys.add(inputBettingMoney(playerName));
        }
        return bettingMoneys;
    }

    private String inputBettingMoney(String playerName){
        System.out.print(System.lineSeparator());
        System.out.printf(BETTING_MONEY_INPUT_MESSAGE, playerName);
        System.out.print(System.lineSeparator());
        return input();
    }

    private String input() {
        String input = SCANNER.nextLine();
        try {
            validatedEmpty(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return input();
        }
        return input;
    }

    private void validatedEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR_MESSAGE);
        }
    }

    public boolean inputHitOrStay(String name) {
        System.out.printf(GET_MORE_CARD_OR_NOT_MESSAGE, name);
        System.out.print(System.lineSeparator());
        String userInput = input();
        try {
            validateRegex(userInput);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return inputHitOrStay(name);
        }
        return matchYesOrNo(userInput);
    }

    private void validateRegex(String input) {
        if (!input.matches(GET_CARD_OR_NOT_REGEX)) {
            throw new IllegalArgumentException(INPUT_ONLY_Y_OR_N_ERROR);
        }
    }

    private boolean matchYesOrNo(String input) {
        return input.equals(YES);
    }
}
