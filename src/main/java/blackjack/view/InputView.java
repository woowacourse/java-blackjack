package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SPLIT_DELIMITER = ",";

    private static String input() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static List<String> inputPlayerNames() {
        System.out.println(
                String.format("게임에 참여할 사람의 이름을 입력하세요.(구분자(%s) 기준으로 분리)", SPLIT_DELIMITER));
        final String initialInput = input();
        return Arrays.stream(initialInput.split(SPLIT_DELIMITER))
                     .map(String::trim)
                     .toList();
    }

    public static BlackjackCommand inputBlackjackCommand(final String playerName) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName));

        return BlackjackCommand.from(input());
    }
}
