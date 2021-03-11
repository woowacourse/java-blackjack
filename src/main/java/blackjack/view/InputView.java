package blackjack.view;

import blackjack.exception.BlackJackException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    public static final String MORE_DRAW_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public static final String YES_OR_NO_ERROR = "[ERROR] y 혹은 n만 입력할 수 있습니다.";
    public static final List<String> YES_OR_NO_VALIDATION = new ArrayList<>(Arrays.asList("y", "n", "Y", "N"));

    private InputView() {};

    public static String requestPlayers() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

    public static boolean requestMoreDraw(String name) {
        System.out.println(name + MORE_DRAW_MESSAGE);
        String input = SCANNER.nextLine();
        try {
            validateYorN(input);
            return translateSignal(input);
        } catch (BlackJackException blackJackException) {
            System.out.println(blackJackException.getMessage());
            return requestMoreDraw(name);
        }
    }

    private static boolean translateSignal(String input) {
        if (input.equals("y")) {
            return true;
        }
        return false;
    }

    private static void validateYorN(String input) {
        if (!YES_OR_NO_VALIDATION.contains(input)) {
            throw new BlackJackException(YES_OR_NO_ERROR);
        }
    }
}
