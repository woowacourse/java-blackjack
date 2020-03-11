package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("이름은 최소 1개 이상이어야 합니다");
        }
        return input;
    }

}
