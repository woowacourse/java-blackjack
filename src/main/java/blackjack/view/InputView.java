package blackjack.view;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String NOT_NUMBER = "\\D";
    private static final Pattern CHARACTER_SET_NOT_NUMBER = Pattern.compile(NOT_NUMBER);
    private static final String YES = "y";
    private static final String STAY = "n";

    private static final Scanner sc = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPeopleNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }

    public static int inputBetAmount(String betAmount) {
        System.out.println(betAmount + "의 배팅 금액은?");
        validateNonNumber(betAmount);
        return Integer.parseInt(readLine());
    }

    private static void validateNonNumber(String input) {
        Matcher matcher = CHARACTER_SET_NOT_NUMBER.matcher(input);
        if (matcher.find()) {
            throw new IllegalArgumentException("숫자가 아닌 값은 입력할 수 없습니다.");
        }
    }

    public static boolean askAdditionalCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = readLine();
        validateCommand(input);
        return isYes(input);
    }

    private static String readLine() {
        String input = sc.nextLine();
        validateBlank(input);
        return input;
    }

    private static boolean isYes(String input) {
        return YES.equals(input);
    }

    private static void validateCommand(String input) {
        if (!STAY.equals(input) && !YES.equals(input)) {
            throw new IllegalArgumentException("y 또는 n 으로 입력해주세요.");
        }
    }

    private static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }

    public static <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return repeat(supplier);
        }
    }
}
