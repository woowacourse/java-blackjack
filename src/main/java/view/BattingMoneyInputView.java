package view;

import java.util.List;

public class BattingMoneyInputView {
    public static List<Integer> getMoney(List<String> playersName) {
        return playersName.stream()
                .map(playerName -> {
                    Console.printf("%s의 배팅 금액은?%n", playerName);
                    String inputFromConsole = Console.getInputFromConsole();
                    validateInput(inputFromConsole);
                    return Integer.parseInt(inputFromConsole);
                }).toList();
    }

    private static void validateInput(String input) {
        int rawNumber;
        try {
            rawNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
        if (rawNumber <= 0) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }
}
