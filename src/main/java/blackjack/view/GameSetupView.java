package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameSetupView {

    private static final Pattern COMMA_WITH_OPTIONAL_SPACES_PATTERN = Pattern.compile("\\s*,\\s*");

    private final Scanner scanner = new Scanner(System.in);

    public void printStartBanner() {
        System.out.println("""
            
            ================================
             Welcome to the Blackjack Game!
            ================================
                게임에 참여할 플레이어 이름은
               2~10글자의 한글, 숫자만 허용되며,
               다른 플레이어와 중복될 수 없습니다.
            ================================
            """);
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitWithComma(scanner.nextLine().trim());
    }

    private List<String> splitWithComma(String input) {
        return Arrays.stream(COMMA_WITH_OPTIONAL_SPACES_PATTERN.split(input, -1))
                .toList();
    }

    public int readBetAmount(String playerName) {
        System.out.printf("%n%s의 배팅 금액은?%n", playerName);
        return parseInt(scanner.nextLine().trim());
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다. 입력: %s".formatted(input));
        }
    }
}
