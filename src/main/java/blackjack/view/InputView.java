package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String ENTER_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_DELIMITER = ",";
    private static final String ASK_ADDITIONAL_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String POSITIVE = "y";
    private static final String NEGATIVE = "n";
    private static final String YES_NO_ANSWER_EXCEPTION = "[ERROR] y 혹은 n으로만 대답하세요";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> enterPlayerNames() {
        System.out.println(ENTER_PLAYER_NAMES_MESSAGE);
        return List.of(scanner.nextLine().split(NAME_DELIMITER));
    }

    public static boolean askToGetAdditionCard(String name) {
        System.out.printf(ASK_ADDITIONAL_CARD_MESSAGE, name);
        String answer = scanner.nextLine();
        validateYesOrNo(answer);
        return answer.equals(POSITIVE);
    }

    private static void validateYesOrNo(String answer) {
        if (!answer.equals(POSITIVE) && !answer.equals(NEGATIVE)) {
            throw new IllegalArgumentException(YES_NO_ANSWER_EXCEPTION);
        }
    }
}
