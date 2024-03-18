package blackjack.view;

import static blackjack.view.expressions.HitStandExpressions.HIT;
import static blackjack.view.expressions.HitStandExpressions.STAND;

import blackjack.view.expressions.HitStandExpressions;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        final String rawInput = printMessageAndReadLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        validateNotBlank(rawInput);
        return List.of(rawInput.split(",", -1));
    }

    public static int readBettingAmount(final String name) {
        final String rawInput = printMessageAndReadLine(String.format("%s의 베팅 금액은?", name));
        validateNotBlank(rawInput);
        validateNumeric(rawInput);
        return Integer.parseInt(rawInput);
    }

    public static boolean readHitStandCommand(final String name) {
        final String rawInput = printMessageAndReadLine(
                String.format("%s는 한장의 카드를 더 받겠습니까?(예는%s, 아니오는 %s)", name, HIT.getValue(), STAND.getValue()));
        return HitStandExpressions.mapHitStandStringToBoolean(rawInput);
    }

    private static void validateNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
    }

    private static void validateNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닌 값은 입력할 수 없습니다.");
        }
    }

    private static String printMessageAndReadLine(final String inputMessage) {
        System.out.println(LINE_SEPARATOR + inputMessage);
        return scanner.nextLine();
    }
}
