package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    private static final String GUIDE_TO_ENTER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String EXCEPTION_ENTER_YES_OR_NO = "y 또는 n을 입력해주세요.";
    private static final String REQUEST_WANT_ONE_MORE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String REQUEST_BETTING_MONEY = "의 배팅 금액은?";

    private InputView() {
    }

    public static List<String> requestNameAndMoney() {
        List<String> playersInfo = new ArrayList<>();
        final String[] names = requestNames();
        for (String name : names) {
            final String money = requestBettingMoney(name.trim());
            playersInfo.add(name + DELIMITER + money);
        }
        return playersInfo;
    }

    private static String[] requestNames() {
        System.out.println(GUIDE_TO_ENTER_NAMES);
        final String namesValue = sc.nextLine();
        return namesValue.split(DELIMITER);
    }

    private static String requestBettingMoney(String name) {
        System.out.println();
        System.out.println(name + REQUEST_BETTING_MONEY);
        return sc.nextLine().trim();
    }

    public static boolean requestOneMoreCard(String name) {
        System.out.println(name + REQUEST_WANT_ONE_MORE);
        String answer = sc.nextLine();
        if (YES.equals(answer)) {
            return true;
        }
        if (NO.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException(EXCEPTION_ENTER_YES_OR_NO);
    }

}
