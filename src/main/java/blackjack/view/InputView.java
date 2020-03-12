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

    public static boolean askForHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = sc.nextLine();
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("다시 입력해 주세요.");
        }
        return Answer.of(input);
    }
}
