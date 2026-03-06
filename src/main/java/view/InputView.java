package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static List<String> askPlayerNames() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String input = sc.nextLine();
        List<String> playerNames = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();

        validatePlayerNamesSize(playerNames);

        return playerNames;
    }

    private static void validatePlayerNamesSize(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }

        if (playerNames.size() > 8) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    public static String askContinue(String player) {
        Scanner sc = new Scanner(System.in);
        System.out.println(player + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String input = sc.nextLine();
        validateContinueResponse(input);
        return input;
    }

    private static void validateContinueResponse(String input) {
        if (!input.matches("[yn]")) {
            throw new IllegalArgumentException("응답은 y와 n만 허용됩니다.");
        }
    }
}

