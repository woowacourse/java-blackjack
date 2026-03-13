package blackjack.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        String[] inputs = input.split(",");
        return Arrays.stream(inputs).toList();
    }

    public boolean readHitAnswer(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        return sc.nextLine().equals("y");
    }

    public BigDecimal readBettingAmount(String name) {
        System.out.printf("%n%s의 배팅 금액은?%n", name);
        return new BigDecimal(sc.nextLine());
    }
}
