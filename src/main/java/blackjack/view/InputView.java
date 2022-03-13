package blackjack.view;

import blackjack.domain.player.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String REQUEST_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.";
    private static final String REQUEST_HIT_OR_STAY_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String NAME_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestNames() {
        System.out.println(REQUEST_NAMES_MESSAGE);
        String input = readLine();
        System.out.println();
        return Arrays.asList(input.split(NAME_DELIMITER));
    }

    private static String readLine() {
        return scanner.nextLine();
    }

    public static String requestHitOrStay(Name name) {
        System.out.println(name.get() + REQUEST_HIT_OR_STAY_MESSAGE);

        return toLowerCase(readLine());
    }

    private static String toLowerCase(String input) {
        return input.toLowerCase();
    }
}
