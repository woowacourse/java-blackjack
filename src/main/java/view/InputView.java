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
}
