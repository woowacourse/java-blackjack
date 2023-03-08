package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private InputView() {
    }

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine();
        return List.of(names.split(DELIMITER));
    }

    public static boolean readDrawCardDecision(final String playerName) {
        System.out.println(LINE_SEPARATOR + playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        if (input.equals("y") || input.equals("n")) {
            return input.equals("y");
        }
        System.out.println("[ERROR] y 또는 n만 입력 가능합니다.");
        return readDrawCardDecision(playerName);
    }

    public static int readPlayerBet(final String playerName) {
        System.out.println(LINE_SEPARATOR + playerName + "의 배팅 금액은?");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력해주세요.");
            return readPlayerBet(playerName);
        }
    }
}
