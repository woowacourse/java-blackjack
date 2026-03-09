package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = ",";
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public boolean readHitDecision(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = scanner.nextLine().trim();
        validateHitDecision(input);
        return input.equals("y");
    }

    private void validateHitDecision(final String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("y 또는 n만 입력할 수 있습니다.");
        }
    }
}
