package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String DELIMITER = ",";
    private static final String ENTER_NAMES_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String SELECT_YES_OR_NO_MSG = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    public static List<String> enterNames() {
        System.out.println(ENTER_NAMES_MSG);
        String input = scanner.nextLine();
        return Arrays.asList(input.split(DELIMITER));
    }

    public static String selectYesOrNo(String name) {
        System.out.println(String.format(SELECT_YES_OR_NO_MSG, name));
        return scanner.nextLine();
    }
}
