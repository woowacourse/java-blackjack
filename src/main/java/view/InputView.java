package view;

import domain.participant.Player;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String PLAYER_NAMES_INPUT_REGEX = "^([가-힣a-zA-Z]+)(,\s*[가-힣a-zA-Z]+)*$";
    private static final String INVALID_PLAYER_NAMES_INPUT = "이름 입력 형식이 올바르지 않습니다.";
    private static final String INVALID_YN_INPUT = "yn 입력 형식이 올바르지 않습니다.";
    private static final String ASK_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String YN_REGEX = "^[yYnN]$";
    private static final String ASK_ONE_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private static final Scanner sc = new Scanner(System.in);

    public static String getPlayerNamesInput() {
        System.out.println(ASK_PLAYER_NAMES_MESSAGE);
        String playerNamesInput = sc.nextLine();
        validatePlayerNames(playerNamesInput);
        return playerNamesInput;
    }

    private static void validatePlayerNames(String playerNamesInput) {
        if (!Pattern.matches(PLAYER_NAMES_INPUT_REGEX, playerNamesInput)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAMES_INPUT);
        }
    }

    public static String getYnInput(Player player) {
        System.out.println(player.getName() + ASK_ONE_MORE_CARD_MESSAGE);
        String ynInput = sc.nextLine();
        if (!Pattern.matches(YN_REGEX, ynInput)) {
            throw new IllegalArgumentException(INVALID_YN_INPUT);
        }
        return ynInput;
    }
}
