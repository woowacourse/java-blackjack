package blackjack.view;

import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static String inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String text = SCANNER.nextLine();
        if (!text.matches("^\\w+(,\\w+)*")) {
            throw new IllegalArgumentException("잘못된 이름 형식입니다.");
        }
        return text;
    }

    public static String chooseOptions(String message, String... options) {
        System.out.println(message);
        String value = SCANNER.nextLine();

        if (isValidOption(value, options)) {
            return value;
        }

        return chooseOptions(message, options);
    }

    private static boolean isValidOption(String value, String[] options) {
        return Stream.of(options)
            .anyMatch(option -> option.equals(value));
    }


}
