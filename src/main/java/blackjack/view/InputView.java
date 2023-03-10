package blackjack.view;

import blackjack.dto.PlayerDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return Arrays.stream(readLine().split(",", -1))
                .map(String::strip)
                .collect(Collectors.toUnmodifiableList());
    }

    public static boolean readCommand(final PlayerDto playerDto) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerDto.getName());
        return Command.from(readLine()).isValue();
    }

    public static String readBetValue(final String playerName) {
        System.out.printf("%n%s의 배팅 금액은?%n", playerName);
        return readLine();
    }

    private static String readLine() {
        String inputValue = scanner.nextLine().strip();
        validateNotEmpty(inputValue);

        return inputValue;
    }

    private static void validateNotEmpty(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }
}
