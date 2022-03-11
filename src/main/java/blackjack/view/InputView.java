package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String SPLIT_REGEX = ",";
    private static final String HIT_ANSWER_YES = "y";
    private static final String HIT_ANSWER_NO = "n";

    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_NAME_INPUT_ERROR_MESSAGE = "[ERROR] 유효하지않은 입력입니다. 참가자 이름을 쉼표로 구분해서 입력해 주세요.";
    private static final String INPUT_PLAYER_HIT_ASK_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String PLAYER_HIT_ANSWER_ERROR_MESSAGE = "[ERROR] 유효하지않은 입력입니다. 입력은 (예는 y, 아니오는 n)로만 입력해 주세요.";
    private static final String PLAYER_HIT_ANSWER_NULL_ERROR_MESSAGE = "[ERROR] 유효하지않은 입력입니다. 입력에 null이 올 수 없습니다.";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        List<String> playerNames = Arrays.asList(scanner.nextLine().split(SPLIT_REGEX));
        validatePlayerNames(playerNames);
        return playerNames;
    }

    public static boolean inputPlayerHit(String playerName) {
        System.out.printf(INPUT_PLAYER_HIT_ASK_MESSAGE, playerName);
        String hitAnswer = scanner.nextLine();
        validateHitAnswer(hitAnswer);
        if (hitAnswer.equals(HIT_ANSWER_YES)) {
            return true;
        }
        return false;
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_INPUT_ERROR_MESSAGE);
        }
    }

    private static void validateHitAnswer(String hitAnswer) {
        if (hitAnswer == null) {
            throw new IllegalArgumentException(PLAYER_HIT_ANSWER_NULL_ERROR_MESSAGE);
        }
        if (!hitAnswer.equals(HIT_ANSWER_YES) && !hitAnswer.equals(HIT_ANSWER_NO)) {
            throw new IllegalArgumentException(PLAYER_HIT_ANSWER_ERROR_MESSAGE);
        }
    }

}
