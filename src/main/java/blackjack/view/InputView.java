package blackjack.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String READ_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String HIT_OR_STAND_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String BET_AMOUNT_FORMAT = "%s의 배팅 금액은?\n";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final Pattern NUMBER_REGEX = Pattern.compile("^-?[0-9]+$");

    private static InputView instance;
    private final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
            return instance;
        }
        return instance;
    }

    public List<String> readNames() {
        System.out.println(READ_NAMES_MESSAGE);
        String[] names = scanner.nextLine().split(",");
        return List.of(names);
    }

    public int readBetAmount(String name) {
        System.out.printf(BET_AMOUNT_FORMAT, name);
        String input;
        do {
            input = scanner.nextLine();
        } while (!isNumber(input));
        return Integer.parseInt(input);
    }

    private boolean isNumber(String input) {
        return NUMBER_REGEX.matcher(input).matches();
    }

    public boolean readHitOrStand(String name) {
        System.out.printf(HIT_OR_STAND_FORMAT, name);
        String input;
        do {
            input = scanner.nextLine();
        } while (!isValidCommand(input));
        return input.equals(YES);
    }

    private boolean isValidCommand(String input) {
        return YES.equals(input) || NO.equals(input);
    }
}
