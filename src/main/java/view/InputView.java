package view;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public BigDecimal getBetAmount(String name) {
        System.out.println(name + "의 배팅 금액은?");

        try {
            return new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액 입력 값이 올바르지 않습니다.");
        }
    }

    public String getChoice(String name) {
        System.out.println(name + "는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
