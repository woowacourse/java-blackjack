package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import blackjack.domain.bet.Money;

public class InputView {

    private static final int SPLIT_LIMIT = -1;
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String line = scanner.nextLine();
        return parseByDelimiter(line, ",");
    }

    private static List<String> parseByDelimiter(String line, String delimiter) {
        return Arrays.stream(line.split(delimiter, SPLIT_LIMIT))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Command askToTake(String playerName) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String keyword = scanner.nextLine();
        return Command.of(keyword);
    }

    public static Money askBetMoney(String playerName) {
        System.out.printf("%n%s의 배팅 금액은?%n", playerName);
        String value = scanner.nextLine();
        try {
            return new Money(Integer.parseInt(value));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("배팅 금액은 숫자만 가능합니다.");
        }
    }
}
