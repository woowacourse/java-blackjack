package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public static String inputReply(String name) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
        return scanner.nextLine();
    }

    public static int inputPlayerBettingAmount(String name) {
        System.out.println(String.format("\n%s의 배팅금액은?", name));
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
