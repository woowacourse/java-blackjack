package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_SPLIT_DELIMITER = ",";
    private static final int LIMIT = -1;
    private static final String HIT_OR_STAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";

    private static final Scanner input = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPlayers() {
        System.out.println(INPUT_NAME_MESSAGE);
        return Arrays.stream(input.nextLine().split(NAME_SPLIT_DELIMITER, LIMIT))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static String inputHitOrStand(String name) {
        System.out.printf(HIT_OR_STAND_MESSAGE, name);
        return input.nextLine();
    }
}
