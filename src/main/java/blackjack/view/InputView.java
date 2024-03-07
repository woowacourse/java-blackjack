package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_HIT_OR_STAND = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String INVALID_HIT_OR_STAND_COMMAND = "y 또는 n을 입력하세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        String input = scanner.nextLine();
        return formatPlayerNames(input);
    }

    private List<String> formatPlayerNames(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public boolean askHitOrStandCommand(final String name) {
        System.out.printf(ASK_HIT_OR_STAND, name);
        String input = scanner.nextLine();
        return formatHitOrStandCommand(input);
    }

    private boolean formatHitOrStandCommand(String input) {
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_OR_STAND_COMMAND);
    }
}
