package blackjack.view;

import blackjack.domain.user.Decision;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    private static final String NAME_SHOULD_BE_PRESENT = "이름은 최소 1개 이상이어야 합니다";
    private static final String INPUT_AGAIN = "다시 입력해 주세요.";

    public static String inputPlayerName() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String input = sc.nextLine();
            validateNullAndEmptyInput(input, NAME_SHOULD_BE_PRESENT);
            return input;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayerName();
        }
    }

    public static boolean askForHit(String name) {
        try {
            System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            String input = sc.nextLine();
            validateNullAndEmptyInput(input, INPUT_AGAIN);
            return Decision.of(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askForHit(name);
        }
    }

    private static void validateNullAndEmptyInput(String input, String message) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
