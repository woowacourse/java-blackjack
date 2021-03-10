package blackjack.view;

import blackjack.domain.user.Name;
import blackjack.domain.user.Names;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String MORE_DRAW_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String REQUEST_MONEY_MESSAGE = "%s의 배팅 금액은?";
    public static final String NUMERAL_ERROR = "[ERROR] 숫자만 입력할 수 있습니다.";

    private InputView() {
    }

    public static String requestPlayers() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String requestMoreDraw(String name) {
        System.out.println(name + MORE_DRAW_MESSAGE);
        return SCANNER.nextLine();
    }

    public static List<Double> requestMoney(Names names) {
        List<Double> moneys = new ArrayList<>();
        for (Name name : names.getNames()) {
            System.out.printf(REQUEST_MONEY_MESSAGE, name.getName());
            moneys.add(getMoneyInput());
        }
        return Collections.unmodifiableList(moneys);
    }

    private static double getMoneyInput() {
        try {
            return SCANNER.nextDouble();
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMERAL_ERROR);
        }
    }
}
