package blackjack.view;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    private static final String YES = "y";
    private static final String STAY = "n";

    private InputView() {
    }

    public static String inputPeopleNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }

    public static int inputBetAmount(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        return Integer.parseInt(readLine());
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

    public static <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            return repeat(supplier);
        }
    }

    private static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }
}
