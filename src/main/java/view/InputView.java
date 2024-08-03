package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    public static String[] getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름을 입력하세요.");
        }
        return input.split(",");
    }

    public static boolean wantsCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String response = sc.nextLine().trim();
        if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
            throw new IllegalArgumentException("'y' 또는 'n'을 입력하세요.");
        }
        return response.equalsIgnoreCase("y");
    }
}
