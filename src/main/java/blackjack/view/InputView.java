package blackjack.view;

import blackjack.domain.HitCommand;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String ASK_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_BETTING_AMOUNT_MESSAGE = "%s의 베팅 금액은?" + System.lineSeparator();
    private static final String ASK_HIT_OR_STAY_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator();
    private static final String EXCEPTION_NOT_DECIMAL_MESSAGE = "입력값이 숫자가 아닙니다.";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES_MESSAGE);
        final String line = scanner.nextLine();
        return parseByDelimiter(line);
    }

    private static List<String> parseByDelimiter(final String line) {
        return Arrays.stream(line.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static BigDecimal askBettingAmount(final String playerName) {
        System.out.printf(ASK_BETTING_AMOUNT_MESSAGE, playerName);
        final String line = scanner.nextLine();
        return parseBigDecimal(line);
    }

    private static BigDecimal parseBigDecimal(final String line) {
        try {
            return new BigDecimal(line);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException(EXCEPTION_NOT_DECIMAL_MESSAGE);
        }
    }

    public static HitCommand askToHit(final String playerName) {
        System.out.println();
        System.out.printf(ASK_HIT_OR_STAY_MESSAGE, playerName);
        return HitCommand.find(scanner.nextLine());
    }
}
