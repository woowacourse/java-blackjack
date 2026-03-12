package domain;

import common.ErrorMessage;
import java.util.regex.Pattern;

public record ParticipantName(String name) {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");

    public ParticipantName {
        validateIsNotBlank(name);
        validateKoreanAndEnglish(name);
    }

    private void validateKoreanAndEnglish(String value) {
        if (!NAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_KO_AND_ENG.getMessage());
        }
    }

    private void validateIsNotBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
        }
    }
}
