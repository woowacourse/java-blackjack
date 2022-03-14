package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DRAW_CARD_FORMAT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";

    private static final String NAME_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES);
        String names = scanner.nextLine();
        return Arrays.asList(names.split(NAME_DELIMITER));
    }

    public static String inputHitOrStaySign(String name) {
        System.out.printf(DRAW_CARD_FORMAT, name);
        String sign = scanner.nextLine();
        return sign.trim();
    }
}
