package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> requestNameAndMoney() {
        List<String> playersInfo = new ArrayList<>();
        final String[] names = requestNames();
        for (String name : names) {
            final String money = requestBettingMoney(name.trim());
            playersInfo.add(name + "," + money);
        }
        return playersInfo;
    }

    private static String[] requestNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String namesValue = sc.nextLine();
        return namesValue.split(",");
    }

    private static String requestBettingMoney(String name) {
        System.out.println();
        System.out.println(name + "의 배팅 금액은?");
        return sc.nextLine().trim();
    }

    public static boolean requestOneMoreCard(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String answer = sc.nextLine();
        if ("y".equals(answer)) {
            return true;
        }
        if ("n".equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해주세요.");
    }

}
