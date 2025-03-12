package blackjack.model.participant;

import java.util.regex.Pattern;

public record Name(
        String value
) {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern ONLY_KOREAN_PATTERN = Pattern.compile("^[가-힣]+$");

    public Name {
        validateName(value);
    }

    private void validateName(String value) {
        if (isBlank(value)) {
            throw new IllegalArgumentException("이름은 비어 있을 수 없으며, 공백만 포함할 수 없습니다.");
        }
        if (isInvalidName(value)) {
            throw new IllegalArgumentException("이름은 한글로만 %d자 이상 %d자 이하로만 가능합니다. 입력: %s"
                    .formatted(MIN_NAME_LENGTH, MAX_NAME_LENGTH, value));
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private boolean isInvalidName(String value) {
        boolean isInvalidLength = value.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < value.length();
        boolean isNotOnlyKorean = !ONLY_KOREAN_PATTERN.matcher(value).matches();
        return isInvalidLength || isNotOnlyKorean;
    }
}
