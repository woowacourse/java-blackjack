package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String READ_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String HIT_OR_STAND_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String YES = "y";
    private static final String NO = "n";

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
