package blackjack.view;

import blackjack.domain.bet.BetMoney;
import blackjack.domain.user.Player;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.";
    private static final String INPUT_HIT_OR_STAY_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String ILLEGAL_HIT_OR_STAY_INPUT_ERROR_MESSAGE = "대답은 y 또는 n 으로만 가능합니다.";
    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final String HIT_ANSWER = "y";
    private static final String STAY_ANSWER = "n";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        String[] input = trim(readLine().split(PLAYER_NAME_DELIMITER));
        System.out.println();
        return Arrays.asList(input);
    }

    public static Map<String, BetMoney> inputBettingMoney(List<String> names) {
        Map<String, BetMoney> playerNameAndBets = new LinkedHashMap<>();
        for (String name : names) {
            BetMoney betMoney = requestBettingMoney(name);
            playerNameAndBets.put(name, betMoney);
            System.out.println();
        }
        return playerNameAndBets;
    }

    private static BetMoney requestBettingMoney(String name) {
        try {
            System.out.printf("%s의 배팅 금액은?" + System.lineSeparator(), name);
            String money = removeBlack(readLine());

            return new BetMoney(money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestBettingMoney(name);
        }
    }

    public static boolean requestIsStay(Player player) {
        try {
            System.out.println(player.getName().get() + INPUT_HIT_OR_STAY_MESSAGE);
            String answer = toLowerCase(removeBlack(readLine()));
            validateHitOrStayAnswer(answer);

            return answer.equals(STAY_ANSWER);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestIsStay(player);
        }
    }

    private static String readLine() {
        return scanner.nextLine();
    }

    private static String[] trim(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].trim();
        }
        return input;
    }

    private static String removeBlack(String input) {
        return input.replace(" ", "");
    }

    private static String toLowerCase(String input) {
        return input.toLowerCase();
    }

    private static void validateHitOrStayAnswer(String answer) {
        if (!answer.equals(HIT_ANSWER) && !answer.equals(STAY_ANSWER)) {
            throw new IllegalArgumentException(ILLEGAL_HIT_OR_STAY_INPUT_ERROR_MESSAGE);
        }
    }
}
