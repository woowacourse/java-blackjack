package util;

import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern CONTAINS_DIGIT = Pattern.compile(".*\\d.*");
    private static final Pattern VALID_INPUT = Pattern.compile("^[a-zA-Z가-힣,]+$");

    public List<String> parseParticipantsName(String participantsName) {
        validateParticipantsName(participantsName);
        return List.of(participantsName.split(","));
    }

    public void validateParticipantsName(String participantsName) {
        validateEmptyInput(participantsName);
        validateNonLiteralInput(participantsName);
        validateInvalidSymbolInput(participantsName);
    }

    static void validateEmptyInput(String participantsName) {
        if (participantsName.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름은 비어 있을 수 없습니다.");
        }
    }

    static void validateNonLiteralInput(String participantsName) {
        if (CONTAINS_DIGIT.matcher(participantsName).matches()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름에는 숫자를 포함할 수 없습니다.");
        }
    }

    static void validateInvalidSymbolInput(String participantsName) {
        if (!VALID_INPUT.matcher(participantsName).matches()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름에는 쉼표를 제외한 특수문자를 사용할 수 없습니다.");
        }
    }
}
