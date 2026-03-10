package util;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣,]+$");

    public void validateAnswer(String answer) {
        validateGetMoreEmptyInput(answer);
        validateYesOrNo(answer);
    }

    public static void validateParticipantsName(String participantsName) {
        validateParticipantEmptyInput(participantsName);
        validateNonLiteralInput(participantsName);
        validateInvalidSymbolInput(participantsName);
    }

    static void validateGetMoreEmptyInput(String answer) {
        if (answer.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드 수령 여부(y/n)는 비어 있을 수 없습니다.");
        }
    }

    static void validateYesOrNo(String answer) {
        if (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
            throw new IllegalArgumentException("[ERROR] 'y' 또는 'n'만 입력해 주세요.");
        }
    }

    static void validateParticipantEmptyInput(String participantsName) {
        if (participantsName.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 비어 있을 수 없습니다.");
        }
    }

    static void validateNonLiteralInput(String participantsName) {
        if (DIGIT_PATTERN.matcher(participantsName).matches()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름에는 숫자를 포함할 수 없습니다.");
        }
    }

    static void validateInvalidSymbolInput(String participantsName) {
        if (!VALID_NAME_PATTERN.matcher(participantsName).matches()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름에는 쉼표를 제외한 특수문자를 사용할 수 없습니다.");
        }
    }
}
