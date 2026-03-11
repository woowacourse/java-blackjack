package view;

import static view.constant.ViewRule.LINE_SEPARATOR;
import static view.constant.ViewRule.NO_ANSWER;
import static view.constant.ViewRule.YES_ANSWER;

import java.util.Scanner;

public class InputView {
    private static final String ENTER_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ENTER_PLAYER_BET_AMOUNT = "%s의 배팅 금액은?";
    private static final String ASK_HIT_OR_NOT = "%s는 한장의 카드를 더 받겠습니까?(예: %s, 아니오: %s)";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String askPlayerNames() {
        System.out.println(ENTER_PLAYER_NAME);
        return SCANNER.nextLine();
    }

    public static String askBetAmount(String name) {
        System.out.printf(ENTER_PLAYER_BET_AMOUNT + LINE_SEPARATOR, name);
        return SCANNER.nextLine();
    }

    public static String askPlayerHit(String name) {
        System.out.printf(ASK_HIT_OR_NOT + LINE_SEPARATOR, name, YES_ANSWER, NO_ANSWER);
        return SCANNER.nextLine();
    }
}
