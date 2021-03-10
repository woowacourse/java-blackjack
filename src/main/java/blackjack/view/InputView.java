package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        try {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

            return Arrays.stream(SCANNER.nextLine().split(DELIMITER))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 이름 형식이 올바르지 않습니다.");

            return inputPlayerNames();
        }
    }

    public static long inputBetMoney(String name) {
        try {
            System.out.println(name + "의 배팅 금액은?");

            return Long.parseLong(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 배팅 금액 형식 올바르지 않습니다.");

            return inputBetMoney(name);
        }
    }

    public static boolean inputDraw(String name) {
        try {
            System.out.println(name + "은 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");

            return isYes(SCANNER.nextLine().toLowerCase());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] y, n 형식이 올바르지 않습니다.");

            return inputDraw(name);
        }
    }

    private static boolean isYes(String input) {
        if (input.equals(YES)) {
            return true;
        }

        if (input.equals(NO)) {
            return false;
        }

        throw new IllegalArgumentException();
    }
}
