package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String PLAYERS_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DRAW_ONE_MORE_CARD_MESSAGE_FORMAT = "%s은 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)\n";

    public static List<String> getPlayerNames() {
        System.out.println(PLAYERS_NAME_INPUT_MESSAGE);
        return Arrays.stream(SCANNER.nextLine().split(DELIMITER))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static boolean getWhetherDrawCard(String name) {
        System.out.printf(DRAW_ONE_MORE_CARD_MESSAGE_FORMAT, name);
        String input = SCANNER.nextLine();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException();
    }

}
