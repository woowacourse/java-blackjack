package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readLineWithBuffer();
        validateNotBlank(input);
        input = removeBlank(input);

        return Arrays.stream(input.split(",")).toList();
    }

    public String readCommand(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        String command = readLineWithBuffer();
        validateNotBlank(command);
        command = removeBlank(command);
        validateCommand(command);

        return command;
    }

    public int readBetAmount(String playerName) {
        System.out.printf("%s의 배팅 금액은?%n", playerName);
        String betAmount = readLineWithBuffer();
        validateNotBlank(betAmount);
        betAmount = removeBlank(betAmount);
        validateInteger(betAmount);

        return Integer.parseInt(betAmount);
    }

    private void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }

    private String removeBlank(String input) {
        return input.replace(" ", "");
    }

    private void validateCommand(String input) {
        if (!"y".equals(input) && !"n".equals(input)) {
            throw new IllegalArgumentException("입력은 y 또는 n 이어야 합니다.");
        }
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 정수여야 합니다.");
        }
    }

    private String readLineWithBuffer() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("IOException이 발생했습니다.");
        }
    }
}
