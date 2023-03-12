package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String regex = "[0-9]+";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPeopleNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitNames(readLine());
    }

    private static List<String> splitNames(String names) {
        return Arrays.stream(names.split(","))
                .collect(Collectors.toUnmodifiableList());
    }

    public static String askAdditionalCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readLine();
    }

    private static String readLine() {
        String input = scanner.nextLine();
        validateBlank(input);
        return input;
    }

    public static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static int readBetAmount(String playerName) {
        System.out.println(playerName + "의 베팅 금액은?");
        String input = scanner.nextLine();
        validateBlank(input);
        validateNumeric(input);
        return Integer.parseInt(input);
    }

    private static void validateNumeric(String input) {
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }
}
