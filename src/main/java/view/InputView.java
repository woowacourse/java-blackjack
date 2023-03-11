package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return splitWithComma(input);
    }

    public boolean askForHit(String userName) {
        return IllegalArgumentExceptionHandler.handleByRepeating(() -> {
            System.out.println(userName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            String input = scanner.nextLine();
            return Command.from(input).isYes();
        });
    }

    private List<String> splitWithComma(String input) {
        return Arrays.asList(input.split(","));
    }

    public int askBetAmount(String name) {
        System.out.println(name + "의 배팅 금액은?");
        String input = scanner.nextLine();
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private void validateNumeric(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다");
        }
    }
}
