package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final String PARTICIPANT_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    static Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String isContinueDraw() {
        return scanner.nextLine();
    }

    public static String enterNames() {
        System.out.println(PARTICIPANT_NAME);
        return scanner.nextLine();
    }

    public static String enterMoney() {
        return scanner.nextLine();
    }
}
