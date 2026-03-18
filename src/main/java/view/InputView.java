package view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final static Scanner sc = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String input = sc.nextLine();
        List<String> playerNames = getPlayerNames(input);

        return playerNames;
    }

    public static int askPlayerBettingMoney(String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);

        String input = sc.nextLine();
        return parsePositiveInt(input);
    }

    public static String askContinue(String player) {
        System.out.println(player + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String input = sc.nextLine();
        validateContinueResponse(input);
        return input;
    }

    private static List<String> getPlayerNames(String input) {
        List<String> playerNames = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();

        validateDuplicateNames(playerNames);
        return playerNames;
    }

    private static void validateDuplicateNames(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("중복된 이름은 입력할 수 없습니다.");
        }
    }

    private static void validateContinueResponse(String input) {
        if (!input.matches("[yn]")) {
            throw new IllegalArgumentException("응답은 y와 n만 허용됩니다.");
        }
    }

    private static int parsePositiveInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요. 입력값 : " + input);
        }
    }
}
