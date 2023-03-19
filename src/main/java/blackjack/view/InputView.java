package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static String[] readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine();
        return names.split(DELIMITER);
    }

    public static String readDrawCardDecision(final String playerName) {
        System.out.println("\n" + playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static int readBettingMoney(String name) {
        System.out.println("\n" + name + "의 배팅 금액은?");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 양의 정수만 입력 가능합니다.");
        }
    }
}
