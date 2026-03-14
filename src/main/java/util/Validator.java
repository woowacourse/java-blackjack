package util;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern CONTAINS_DIGIT = Pattern.compile(".*\\d.*");
    private static final Pattern VALID_INPUT = Pattern.compile("^[a-zA-Z가-힣,]+$");

    public void validatePlayersName(String playersName) {
        validateEmptyNameInput(playersName);
        validateNonLiteralInput(playersName);
        validateInvalidSymbolInput(playersName);
    }

    private void validateEmptyNameInput(String playersName) {
        if (playersName.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 비어 있을 수 없습니다.");
        }
    }

    private void validateNonLiteralInput(String playersName) {
        if (CONTAINS_DIGIT.matcher(playersName).matches()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름에는 숫자를 포함할 수 없습니다.");
        }
    }

    private void validateInvalidSymbolInput(String playersName) {
        if (!VALID_INPUT.matcher(playersName).matches()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름에는 쉼표를 제외한 특수문자를 사용할 수 없습니다.");
        }
    }

    public void validateAnswer(String answer) {
        validateEmptyAnswerInput(answer);
        validateYesOrNo(answer);
    }

    private void validateEmptyAnswerInput(String answer) {
        if (answer.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드 수령 여부(y/n)는 비어 있을 수 없습니다.");
        }
    }

    private void validateYesOrNo(String answer) {
        if (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
            throw new IllegalArgumentException("[ERROR] 'y' 또는 'n'만 입력해 주세요.");
        }
    }

    public void validateEmptyBettingMoney(String bettingMoney) {
        if (bettingMoney.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 비어 있을 수 없습니다.");
        }
    }

    public void validateNegativeBettingMoney(int bettingMoney) {
        if (bettingMoney < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 음수가 될 수 없습니다.");
        }
    }
}
