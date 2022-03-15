package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_REGEX = ",";
    private static final String HIT_ANSWER_YES = "y";
    private static final String HIT_ANSWER_NO = "n";
    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_NAME_INPUT_ERROR_MESSAGE = "[ERROR] 유효하지않은 입력입니다. 참가자 이름을 쉼표로 구분해서 입력해 주세요.";
    private static final String INPUT_PLAYER_HIT_ASK_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String PLAYER_HIT_ANSWER_ERROR_MESSAGE = "[ERROR] 유효하지않은 입력입니다. 입력은 (예는 y, 아니오는 n)로만 입력해 주세요.";
    private static final String PLAYER_HIT_ANSWER_NULL_ERROR_MESSAGE = "[ERROR] 유효하지않은 입력입니다. 입력에 null이 올 수 없습니다.";
    private static final String INPUT_BETTING_MONEY_MESSAGE = "%s의 배팅 금액은?\n";
    private static final String BETTING_MONEY_FORMAT_ERROR_MESSAGE = "베팅 금액은 숫자만 입력할 수 있습니다.";

    public static List<String> inputPlayerName() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        List<String> playerNames = Arrays.asList(scanner.nextLine().split(SPLIT_REGEX));
        validatePlayerNames(playerNames);
        return playerNames;
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(PLAYER_NAME_INPUT_ERROR_MESSAGE);
        }
    }

    public static boolean inputPlayerHit(String playerName) {
        System.out.printf(INPUT_PLAYER_HIT_ASK_MESSAGE, playerName);
        String hitAnswer = scanner.nextLine();
        validateHitAnswer(hitAnswer);
        return hitAnswer.equals(HIT_ANSWER_YES);
    }

    private static void validateHitAnswer(String hitAnswer) {
        if (hitAnswer == null) {
            throw new IllegalArgumentException(PLAYER_HIT_ANSWER_NULL_ERROR_MESSAGE);
        }
        if (!hitAnswer.equals(HIT_ANSWER_YES) && !hitAnswer.equals(HIT_ANSWER_NO)) {
            throw new IllegalArgumentException(PLAYER_HIT_ANSWER_ERROR_MESSAGE);
        }
    }

    public static int inputBettingMoney(String playerName) {
        System.out.printf(INPUT_BETTING_MONEY_MESSAGE, playerName);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(BETTING_MONEY_FORMAT_ERROR_MESSAGE);
        }
    }
}
