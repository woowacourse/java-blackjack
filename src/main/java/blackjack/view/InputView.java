package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final String YES = "y";
    private static final String NO = "n";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    public static boolean askDrawOrStay(String playerName) {
        askWillDraw(playerName);

        return DrawRequest.drawOrStay(SCANNER.nextLine());
    }

    private static void askWillDraw(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)\n", playerName, YES, NO);
    }

    public static String getBettingMoney(String name) {
        System.out.printf("\n%s의 배팅 금액은?\n", name);
        return SCANNER.nextLine();
    }
}
