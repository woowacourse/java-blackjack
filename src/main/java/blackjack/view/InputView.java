package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String READ_TRY_COMMAND_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String READ_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INVALID_BETTING_MESSAGE = "배팅 금액은 숫자여야합니다.";
    private static final String READ_BETTING_MESSAGE = "의 배팅 금액은?";
    private static final String SPLIT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersName() {
        System.out.println(READ_PLAYER_NAME_MESSAGE);
        String input = scanner.nextLine();
        return List.of(input.split(SPLIT_DELIMITER));
    }

    public String readTryCommand(String name) {
        System.out.println(name + READ_TRY_COMMAND_MESSAGE);
        return scanner.nextLine();
    }

    public int readBetting(String name) {
        try {
            System.out.println(name + READ_BETTING_MESSAGE);
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.out.println("[ERROR]: " + INVALID_BETTING_MESSAGE);
            return readBetting(name);
        }
    }
}
