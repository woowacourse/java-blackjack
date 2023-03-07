package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ENTER_PLAYER_NAME_NOTICE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ENTER_ADD_CARD_NOTICE = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String DELIMITER = ",";
    private static final String YES = "y";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println(ENTER_PLAYER_NAME_NOTICE);

        String input = scanner.nextLine();
        return splitWithComma(input);
    }

    public boolean askForAnotherCard(String name) {
        System.out.printf(ENTER_ADD_CARD_NOTICE, name);
        String input = scanner.nextLine();

        if (input.equals(YES)) {
            return true;
        }

        return false;
    }

    private List<String> splitWithComma(String input) {
        return Arrays.asList(input.split(DELIMITER));
    }

}
