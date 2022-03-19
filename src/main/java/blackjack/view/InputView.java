package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String ENTER_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_DELIMITER = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> enterPlayerNames() {
        System.out.println(ENTER_PLAYER_NAMES_MESSAGE);
        return List.of(scanner.nextLine().split(NAME_DELIMITER));
    }
}
