package blackjack.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String text = SCANNER.nextLine();
        if (!text.matches("^[a-zA-Z가-힣]+(,[a-zA-Z가-힣]+)*$")) {
            throw new IllegalArgumentException("잘못된 이름 형식입니다.");
        }
        return List.of(text.split(","));
    }

    public static BigDecimal inputMoneys(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        String text = SCANNER.nextLine();
        if (!text.matches("^[1-9][0-9]*$")) {
            throw new IllegalArgumentException("잘못된 금액 형식입니다.");
        }
        return new BigDecimal(text);
    }

    public static Answer isKeepTakeCard(String name) {
        String value = chooseOptions(message(name), "y", "n");
        return Answer.of(value);
    }

    private static String message(String name) {
        return String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
    }

    public static String chooseOptions(String message, String... options) {
        System.out.printf(message);
        String value = SCANNER.nextLine();

        if (isValidOption(value, options)) {
            return value;
        }

        return chooseOptions(message, options);
    }

    private static boolean isValidOption(String value, String... options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }
}
