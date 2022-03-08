package view;

import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String GET_MORE_CARD_OR_NOT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String YES = "y";
    private static final String EMPTY_INPUT_ERROR_MESSAGE = "[ERROR] 빈 값을 입력할 수 없습니다.";
    private static final String INPUT_ONLY_Y_OR_N_ERROR = "[ERROR] y나 n만 입력할 수 있습니다.";

    private static final String GET_CARD_OR_NOT_REGEX = "^[y,n]$";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final InputView INPUT_VIEW = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INPUT_VIEW;
    }

    private String input() {
        String input = SCANNER.nextLine();
        validatedEmpty(input);
        return input;
    }

    private void validatedEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR_MESSAGE);
        }
    }

    public String inputPlayerName() {
        System.out.println(PLAYER_NAME_INPUT_MESSAGE);
        return input();
    }

    public boolean inputMoreCardOrNot() {
        System.out.println(GET_MORE_CARD_OR_NOT_MESSAGE);
        String userInput = input();
        validateRegex(userInput);
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
