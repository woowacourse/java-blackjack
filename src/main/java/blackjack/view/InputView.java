package blackjack.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String READ_TRY_COMMAND_MSG = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String READ_PLAYER_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String SPLIT_DELIMITER = ",";
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersName() {
        System.out.println(READ_PLAYER_NAME_MSG);
        String input = scanner.nextLine();
        return List.of(input.split(SPLIT_DELIMITER));
    }

    public String readTryCommand(String name) {
        System.out.println(name + READ_TRY_COMMAND_MSG);
        return scanner.nextLine();
    }

    public int readBetting(String name) {
        try {
            System.out.println(name + "의 배팅 금액은?");
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.out.println("[ERROR]: " + "배팅 금액은 숫자여야합니다.");
            return readBetting(name);
        }
    }
}
