package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String POSITIVE_ANSWER = "y";
    private static final String NEGATIVE_ANSWER = "n";
    private static final String NAME_DELIMITER = ",";
    private static final String QUESTION_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String QUESTION_ADDITIONAL_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)\n";
    private static final String YES_OR_NO_ANSWER_EXCEPTION = "[ERROR] 답은 %s, %s 으로 해야 합니다.";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getPlayerNames() {
        System.out.println(QUESTION_PLAYER_NAMES);
        return List.of(scanner.nextLine()
                .split(NAME_DELIMITER));
    }

    public static boolean askAdditionalCard(String name) {
        System.out.printf(QUESTION_ADDITIONAL_CARD, name, POSITIVE_ANSWER, NEGATIVE_ANSWER);

        String answer = scanner.nextLine();
        validateIsYesOrNo(answer);
        return answer.equals(POSITIVE_ANSWER);
    }

    private static void validateIsYesOrNo(String answer) {
        if(!(answer.equals(POSITIVE_ANSWER) || answer.equals(NEGATIVE_ANSWER))) {
            throw new IllegalArgumentException(String.format(YES_OR_NO_ANSWER_EXCEPTION, POSITIVE_ANSWER, NEGATIVE_ANSWER));
        }
    }
}
