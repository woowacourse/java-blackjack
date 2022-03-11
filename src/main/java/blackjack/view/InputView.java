package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String QUESTION_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String FORMAT_HIT = "%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n";
    private static final String ERROR_NO_SUCH_SIGN = "[ERROR] y또는 n으로만 입력하세요.";

    private static final String REGEX_NAME = ",";
    private static final String SIGN_TRUE = "y";
    private static final String SIGN_FALSE = "n";

    public List<String> askEntryNames() {
        System.out.println(QUESTION_NAME);
        String namesInput = new Scanner(System.in).nextLine();
        return splitNames(namesInput);
    }

    private List<String> splitNames(String namesInput) {
        return Arrays.asList((namesInput.split(REGEX_NAME)));
    }

    public boolean askForHit(String name) {
        System.out.printf(FORMAT_HIT, name, SIGN_TRUE, SIGN_FALSE);
        String input = new Scanner(System.in).nextLine();
        return isInputTrue(input.trim());
    }

    private boolean isInputTrue(String input) {
        if (input.equals(SIGN_TRUE)) {
            return true;
        }
        if (input.equals(SIGN_FALSE)) {
            return false;
        }
        throw new IllegalArgumentException(ERROR_NO_SUCH_SIGN);
    }
}
