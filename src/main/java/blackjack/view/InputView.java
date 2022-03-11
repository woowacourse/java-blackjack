package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_WANT_MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String PLAYERS_NAME_DELIMITER = ",";

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAME_MESSAGE);
        return Arrays.stream(SCANNER.nextLine().split(PLAYERS_NAME_DELIMITER))
                .collect(Collectors.toList());
    }

    public static String requestMoreCard(String name) {
        System.out.println(name + REQUEST_WANT_MORE_CARD_MESSAGE);
        return SCANNER.nextLine();
    }
}
