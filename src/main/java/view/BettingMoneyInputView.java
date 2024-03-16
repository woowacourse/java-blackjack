package view;

import java.util.ArrayList;
import java.util.List;

public class BettingMoneyInputView {
    public static List<Integer> getMoney(List<String> playersNames) {
        List<Integer> rawBettingMoneys = new ArrayList<>(playersNames.size());
        for (String playerName : playersNames) {
            Console.printf("%s의 배팅 금액은?%n", playerName);
            String inputFromConsole = Console.getInputFromConsole();
            validateInput(inputFromConsole);
            rawBettingMoneys.add(Integer.parseInt(inputFromConsole));
        }
        return List.copyOf(rawBettingMoneys);
    }

    private static void validateInput(String input) {
        try {
            int rawNumber = Integer.parseInt(input);
            validateNumberIsPositive(rawNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }

    private static void validateNumberIsPositive(int rawNumber) {
        if (rawNumber <= 0) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }
}
