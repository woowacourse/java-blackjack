package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner sc = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        String[] inputs = input.split(",");
        return Arrays.stream(inputs).toList();
    }

    public boolean readHitAnswer(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = sc.nextLine();
        validateHitAnswer(input);

        return input.equals("y");
    }

    private void validateHitAnswer(String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("[ERROR] y 또는 n로 입력해주세요.");
        }
    }

    public int readBetAmount(String name) {
        System.out.printf("%s의 베팅 금액은?%n", name);
        String input = sc.nextLine();

        int betAmount = Integer.parseInt(input);

        return betAmount;
    }


}
