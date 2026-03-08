package view;

import java.util.Scanner;

public class InputView {
    private static final String HIT_ANSWER = "y";
    private static final Scanner sc = new Scanner(System.in);

    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }

    public boolean readHitOrNot(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readLine().equals(HIT_ANSWER);
    }

    private String readLine() {
        return sc.nextLine();
    }
}
