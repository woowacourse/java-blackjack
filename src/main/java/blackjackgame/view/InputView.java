package blackjackgame.view;

import blackjackgame.domain.UserAction;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        String inputNames = scanner.nextLine();
        String[] seperatedInputNames = inputNames.split(DELIMITER);
        List<String> playerNames = Arrays.asList(seperatedInputNames);

        return getNamesWithSpaceRemoved(playerNames);
    }

    public int readPlayerBetAmount() {
        String inputAmount = scanner.nextLine();
        int amount = parseInt(inputAmount);
        validateBetAmount(amount);
        return amount;
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("베팅 금액은 숫자만 입력 가능합니다.", numberFormatException);
        }
    }

    private void validateBetAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1원 이상입니다.");
        }
    }

    private List<String> getNamesWithSpaceRemoved(List<String> playerNames) {
        return playerNames.stream()
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public UserAction readDrawCommand() {
        return UserAction.of(scanner.nextLine());
    }
}
