package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public List<String> readNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        validateBlank(input);
        return parseStringToList(input);
    }

    public int readBetAmount(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s의 배팅 금액은?%n", name);
        String input = scanner.nextLine();
        return convertToInt(input);
    }

    private int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주십시오.");
        }
    }

    public boolean readGetOneMore(final String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = scanner.nextLine();

        validateBlank(input);
        return YorN.fromText(input);
    }

    private List<String> parseStringToList(final String input) {
        return Arrays.asList(input.split(","));
    }

    private void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }

    enum YorN {
        YES("y", true),
        NO("n", false);

        private final String text;
        private final boolean isYes;

        YorN(String text, boolean isYes) {
            this.text = text;
            this.isYes = isYes;
        }

        private static boolean fromText(final String input) {
            if (YES.text.equals(input)) {
                return YES.isYes;
            }
            if (NO.text.equals(input)) {
                return NO.isYes;
            }
            throw new IllegalArgumentException("입력은 y/n만 가능합니다.");
        }
    }
}
