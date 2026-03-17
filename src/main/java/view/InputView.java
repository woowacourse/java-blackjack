package view;

import java.util.Scanner;

public final class InputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private Scanner sc = new Scanner(System.in);

    public String readParticipantNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return sc.nextLine();
    }

    public String readHitOrNot(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return sc.nextLine();
    }

    public String readBettingAmount(String name) {
        System.out.printf(LINE_SEPARATOR + "%s의 배팅 금액은?%s", name, LINE_SEPARATOR);
        return sc.nextLine();
    }
}
