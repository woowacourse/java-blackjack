package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();
        List<String> playerNames = getPlayerNames(input);
        return playerNames;
    }

    public static String askContinue(String player) {
        System.out.println();
        System.out.println(player + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String input = SCANNER.nextLine();
        InputValidator.validateContinueResponse(input);
        return input;
    }

    public static Integer askBettingAmount(String player) {
        System.out.println();
        System.out.println(player + "의 배팅 금액은?");
        Integer input = SCANNER.nextInt();
        SCANNER.nextLine();
        return input;
    }

    private static List<String> getPlayerNames(String input) {
        List<String> playerNames = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        return playerNames;
    }
}
