package blackjack.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SPLIT_DELIMITER = ",";
    
    private InputView() {
    }


    private static String input() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static List<String> inputPlayerNames() {
        System.out.printf("게임에 참여할 사람의 이름을 입력하세요.(구분자(%s) 기준으로 분리)%n", SPLIT_DELIMITER);
        final String initialInput = input();
        return Arrays.stream(initialInput.split(SPLIT_DELIMITER))
                     .map(String::trim)
                     .toList();
    }

    public static int inputBettingMoney(final String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        final String initialInput = input();
        return Integer.parseInt(initialInput);
    }

    public static BlackjackCommand inputBlackjackCommand(final String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);

        return BlackjackCommand.from(input());
    }
}
