package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String REQUEST_PLAYER_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String REQUEST_CARD_TAKE_OPTION_MSG = "%s님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    public static final String NAME_REGEX = "^[a-zA-Z가-힣]+(,[a-zA-Z가-힣]+)*";
    public static final String OPTION_REGEX = "(y|n)";

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputNames() {
        System.out.println(REQUEST_PLAYER_NAME_MSG);
        String text = SCANNER.nextLine();
        try {
            validateNameFormat(text);
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return inputNames();
        }
        return List.of(text.split(","));
    }

    private static void validateNameFormat(String text) {
        if (!text.matches(NAME_REGEX)) {
            throw new IllegalArgumentException("잘못된 이름 형식입니다.");
        }
    }

    public static String chooseOptions(String name) {
        System.out.printf(REQUEST_CARD_TAKE_OPTION_MSG, name);
        String value = SCANNER.nextLine();
        try {
            validateOption(value);
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return chooseOptions(name);
        }
        return value;
    }

    private static void validateOption(String value) {
        if (!value.matches(OPTION_REGEX)) {
            throw new IllegalArgumentException("y 혹은 n을 입력해주세요.");
        }
    }
}
