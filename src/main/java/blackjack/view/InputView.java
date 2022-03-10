package blackjack.view;

import java.util.Scanner;

public class InputView {

    public static final String DELIMITER = ",";
    private static final String REGEX = "^[a-zA-Z]+$";
    public static final String HIT = "y";
    public static final String STAY = "n";
    private static final String MUST_NOT_EMPTY = "입력은 빈 입력일 수 없습니다.";
    private static final String WRONG_FORM = "입력형식에 맞춰 입력해주세요.";
    private static final String WRONG_HIT_OR_STAY = HIT + " 혹은 " + STAY + "만 입력 가능합니다.";

    public static String[] inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
        String input = inputLine();
        validateEmpty(input);
        String[] names = input.split(DELIMITER);
        validateInputName(names);
        return names;
    }

    public static boolean requestHitOrStay(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = inputLine();
        validateEmpty(input);
        validateHitOrStay(input);
        return input.equals(HIT);
    }

    private static void validateEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(MUST_NOT_EMPTY);
        }
    }

    private static void validateInputName(String[] names) {
        for (String name : names) {
            validateInputNameForm(name);
        }
    }

    private static void validateInputNameForm(String name) {
        if (!name.matches(REGEX)) {
            throw new IllegalArgumentException(WRONG_FORM);
        }
    }

    private static void validateHitOrStay(String input) {
        if (!input.equals(HIT) && !input.equals(STAY)) {
            throw new IllegalArgumentException(WRONG_HIT_OR_STAY);
        }
    }

    private static String inputLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
