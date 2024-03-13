package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private final Scanner scanner = new Scanner(System.in);

    private String readLine(String message) {
        System.out.println(message);

        return scanner.nextLine().trim();
    }

    public List<String> readPlayerNames() {
        String input = readLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return Arrays.stream(input.split(PLAYER_NAME_DELIMITER, -1))
                .map(String::trim)
                .toList();
    }

    public int readBettingMoney(String name) {
        System.out.println();
        String message = String.format("%s의 배팅 금액은?", name);
        String input = readLine(message);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 숫자로 입력해주세요.");
        }
    }

    public boolean readCommand(String name) {
        System.out.println();
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        String input = readLine(message);

        if (YES.equalsIgnoreCase(input)) {
            return true;
        }

        if (NO.equalsIgnoreCase(input)) {
            return false;
        }

        throw new IllegalArgumentException("y나 n를 입력해주세요.");
    }
}
