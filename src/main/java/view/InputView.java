package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Player;
import model.Players;

public class InputView {
    private static final String PLAYER_NAMES_INPUT_REGEX = "^([가-힣a-zA-Z]+)(,\s*[가-힣a-zA-Z]+)*$";
    private static final String INVALID_PLAYER_NAMES_INPUT = "이름 입력 형식이 올바르지 않습니다.";
    private static final String INVALID_YN_INPUT = "yn 입력 형식이 올바르지 않습니다.";
    private static final String ASK_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String YN_REGEX = "^[yYnN]$";
    private static final String ASK_ONE_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private static final Scanner sc = new Scanner(System.in);

    public static Map<Player, Integer> inputBettingPrice(Players players) {
        Map<Player, Integer> bettingPrices = new HashMap<>();
        for (Player player: players.getPlayers()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(player.getName())
                    .append("의 배팅 금액은?");
            System.out.println(stringBuilder);
            bettingPrices.put(player, Integer.parseInt(sc.nextLine()));
        }
        return bettingPrices;
    }

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

    public static String getContinueOrNot(Player player) {
        System.out.println(player.getName() + ASK_ONE_MORE_CARD_MESSAGE);
        String continueOrNot = sc.nextLine();
        if (!Pattern.matches(YN_REGEX, continueOrNot)) {
            throw new IllegalArgumentException(INVALID_YN_INPUT);
        }
        return continueOrNot;
    }
}
