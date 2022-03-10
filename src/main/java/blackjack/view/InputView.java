package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String NAME_SPLIT_DELIMITER = ",";
    public static final String HIT_OR_STAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";

    private InputView() {
    }

    public static List<String> inputPlayerName() {
        System.out.println(INPUT_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine().split(NAME_SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String inputHitOrStand(String name) {
        System.out.println();
        System.out.printf(HIT_OR_STAND_MESSAGE, name);
        return scanner.nextLine();
    }
}
