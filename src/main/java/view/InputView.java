package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String decideToGetMoreCard(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        validateCommand(input);
        return input;
    }

    private void validateCommand(final String input) {
        if (isInvalidCommand(input)) {
            throw new IllegalArgumentException("y 또는 n을 입력해주세요.");
        }
    }

    // TODO: 부정 예약어 없애보자.
    private boolean isInvalidCommand(final String input) {
        return !input.equals("y") && !input.equals("n");
    }
}
