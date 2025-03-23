package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public Answer readHitOrStay(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String input = scanner.nextLine();
        return Answer.of(input);
    }

    public int readBetAmount(String playerName) {
        System.out.printf("%n%s의 배팅 금액은?%n", playerName);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }
}
