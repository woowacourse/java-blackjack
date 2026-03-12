package blackjack.view;

import blackjack.model.Player;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern YN_PATTERN = Pattern.compile("y|n");

    private final Scanner sc = new Scanner(System.in);

    public String readPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return sc.nextLine();
    }

    public int readBetAmount(String name) {
        System.out.println();
        System.out.println(name + "의 배팅 금액은?");
        String input = sc.nextLine().trim();
        validateBlank(input);
        int number = parseToInt(input);
        validatePositive(number);
        return number;

    }

    public boolean readCardAdd(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = sc.nextLine().trim();
        validateBlank(input);
        validatePattern(input);
        return input.equalsIgnoreCase("y");
    }

    public void closeScanner() {
        sc.close();
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다.");
        }
    }

    private void validatePositive(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1 이상이여야 합니다.");
        }
    }

    private void validatePattern(String input) {
        if (!YN_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("입력값이 올바르지 않습니다.");
        }
    }
}

