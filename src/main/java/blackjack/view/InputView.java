package blackjack.view;

import blackjack.domain.HitCommand;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String line = scanner.nextLine();
        return parseByDelimiter(line);
    }

    private static List<String> parseByDelimiter(final String line) {
        return Arrays.stream(line.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int askBettingAmount(final String playerName) {
        System.out.printf("%s의 베팅 금액은?" + System.lineSeparator(), playerName);
        final String line = scanner.nextLine();
        return parseInteger(line);
    }

    private static int parseInteger(final String line) {
        try {
            return Integer.parseInt(line);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }

    public static HitCommand askToHit(final String playerName) {
        System.out.println();
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator(), playerName);
        return HitCommand.find(scanner.nextLine());
    }
}
