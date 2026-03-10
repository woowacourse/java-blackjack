package view;

import static domain.GameManager.validatePlayersNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static List<String> askPlayerNames() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String input = sc.nextLine();
        List<String> playerNames = getPlayerNames(input);
        validatePlayersNumber(playerNames);

        return playerNames;
    }

    public static String askContinue(String player) {
        Scanner sc = new Scanner(System.in);
        System.out.println(player + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String input = sc.nextLine();
        validateContinueResponse(input);
        return input;
    }

    private static List<String> getPlayerNames(String input) {
        List<String> playerNames = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        return playerNames;
    }

    private static void validateContinueResponse(String input) {
        if (!input.matches("[yn]")) {
            throw new IllegalArgumentException("응답은 y와 n만 허용됩니다.");
        }
    }
}

