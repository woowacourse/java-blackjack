package presentation.ui;

import java.util.List;

import static domain.member.MemberInfo.DEALER_NAME;

public class ValidatedInput {

    public void validatePlayerName(List<String> playerNames) {
        playerNames.forEach(name -> {
            validateNotBlank(name);
            validateIsNotNumber(name);
            validateIsNotDealerName(name);
        });
    }

    public void validatePlayerBetAmount(String playerBetAmount) {
        validateNotBlank(playerBetAmount);
        validateIsNumber(playerBetAmount);
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력 값이 빈 값입니다.");
        }
    }

    private void validateIsNotNumber(String playerName) {
        if (isNumeric(playerName)) {
            throw new IllegalArgumentException("플레이어 이름은 숫자로 설정할 수 없습니다.");
        }
    }

    private boolean isNumeric(String input) {
        return input != null && input.matches("^-?\\d+$");
    }

    private void validateIsNotDealerName(String playerName) {
        if (DEALER_NAME.equals(playerName)) {
            throw new IllegalArgumentException("플레이어의 이름을 '딜러'로 설정할 수 없습니다.");
        }
    }

    private void validateIsNumber(String playerBetAmount) {
        if (!isNumeric(playerBetAmount)) {
            throw new IllegalArgumentException("플레이어의 베팅 금액은 숫자여야 됩니다.");
        }
    }
}
