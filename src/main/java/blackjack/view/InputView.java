package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String HIT_COMMAND = "y";
    private static final String STAY_COMMAND = "n";
    private static final String PLAYER_INTENTION_ERROR_MESSAGE = "y 혹은 n 만 입력 가능 합니다.";
    public static final String BETTING_AMOUNT_TYPE_ERROR_MESSAGE = "베팅 금액은 양의 정수만 입력가능합니다.";

    public static InputView getInstance() {
        return INSTANCE;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String input = scanner.nextLine();
        return List.of(splitNames(input));
    }

    private String[] splitNames(String input) {
        return input.split(DELIMITER);
    }

    public boolean readIsHit(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        String input = scanner.nextLine();
        if (!input.equals(HIT_COMMAND) && !input.equals(STAY_COMMAND)) {
            throw new IllegalArgumentException(PLAYER_INTENTION_ERROR_MESSAGE);
        }
        return input.equals(HIT_COMMAND);
    }

    public int readBetAmount(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");

        String input = scanner.nextLine();

        try {
            return Integer.parseInt(input);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(BETTING_AMOUNT_TYPE_ERROR_MESSAGE);
        }
    }
}
