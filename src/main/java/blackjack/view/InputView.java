package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BATTING_MONEY_MESSAGE = "%s의 배팅 금액은?";

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputNames() {
        OutputView.printMessage(INPUT_PLAYER_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static int inputBattingMoney(String name) {
        OutputView.printMessage(String.format(INPUT_BATTING_MONEY_MESSAGE, name));
        return scanner.nextInt();
    }
}
