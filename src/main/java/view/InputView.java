package view;

import java.util.Scanner;
import util.ErrorMessage;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String INPUT_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NEEDS_TO_HIT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String YES = "y";
    private static final String NO = "n";

    public String readPlayerName() {
        return printAndGet(INPUT_PLAYER_NAME);
    }

    public boolean readNeedToHit(String name) {
        String yesOrNo = printAndGet(NEEDS_TO_HIT.formatted(name));
        validateInputFormat(yesOrNo);

        return yesOrNo.equals(YES);
    }

    private String printAndGet(String needsToHit) {
        System.out.println(needsToHit);
        String input = scanner.nextLine();

        validateIsBlank(input);
        return input;
    }

    private void validateInputFormat(String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_WRONG.getMessage());
        }
    }

    private void validateIsBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_BLANK.getMessage());
        }
    }
}
