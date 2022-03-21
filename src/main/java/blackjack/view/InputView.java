package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String REQUEST_PLAYER_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String REQUEST_PLAYER_BETTING_MONEY_MSG = "%n%s의 베팅 금액은?%n";
    public static final String REQUEST_CARD_TAKE_OPTION_MSG = "%s님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    public static final String NAME_REGEX = "^[a-zA-Z가-힣]+(,[a-zA-Z가-힣]+)*";
    public static final String MONEY_REGEX = "^\\d+0$";
    public static final String OPTION_REGEX = "(y|n)";
    public static final String OPTION_YES = "y";

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

    public static int inputBettingMoney(String playerName) {
        System.out.printf(REQUEST_PLAYER_BETTING_MONEY_MSG, playerName);
        String inputMoney = SCANNER.nextLine();
        try {
            validateMoney(inputMoney);
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return inputBettingMoney(playerName);
        }
        return Integer.parseInt(inputMoney);
    }

    private static void validateMoney(String money) {
        if (!money.matches(MONEY_REGEX)) {
            throw new IllegalArgumentException("베팅 금액은 10원 단위로 나누어 떨어지는 양의 정수여야 합니다.");
        }
    }

    private static void validateNameFormat(String name) {
        if (!name.matches(NAME_REGEX)) {
            throw new IllegalArgumentException("잘못된 이름 형식입니다.");
        }
    }

    public static Boolean chooseOptions(String name) {
        System.out.printf(REQUEST_CARD_TAKE_OPTION_MSG, name);
        String value = SCANNER.nextLine();
        try {
            validateOption(value);
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            return chooseOptions(name);
        }
        return value.equals(OPTION_YES);
    }

    private static void validateOption(String value) {
        if (!value.matches(OPTION_REGEX)) {
            throw new IllegalArgumentException("y 혹은 n을 입력해주세요.");
        }
    }
}
