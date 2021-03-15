package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String askGamblersName() {
        OutputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    public static String askPlayerBattingMoney(String playerName) {
        OutputView.printMessage(playerName + "의 배팅 금액은?");
        return SCANNER.nextLine();
    }

    public static String askDrawOrNot(String playerName) {
        OutputView.printMessage(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return SCANNER.nextLine();
    }
}
