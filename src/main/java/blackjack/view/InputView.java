package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    public static final String YES = "y";
    public static final String NO = "n";

    private InputView() {
    }

    public static String getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return sc.nextLine();
    }

    public static boolean requestOneMoreCard(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String answer = sc.nextLine();
        if (YES.equals(answer)) {
            return true;
        }
        if (NO.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException();
    }

    public static String getBetMoney(Player player) {
        System.out.println("\n" + player.getName() + "의 베팅 금액?");
        return sc.nextLine();
    }
}
