package presentation.ui;

import java.util.List;

public class ValidatedInput {
    private static final String DEALER_NAME = "딜러";

    public void validatePlayerName(List<String> playerNames) {
        playerNames.forEach(name -> {
            validateIsNotNumber(name);
            validateIsNotDealerName(name);
        });
    }

    private void validateIsNotNumber(String playerName) {
        if (isNumeric(playerName)) {
            throw new IllegalArgumentException("플레이어 이름은 숫자로 설정할 수 없습니다.");
        }
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    private void validateIsNotDealerName(String playerName) {
        if (DEALER_NAME.equals(playerName)) {
            throw new IllegalArgumentException("플레이어의 이름을 '딜러'로 설정할 수 없습니다.");
        }
    }
}
