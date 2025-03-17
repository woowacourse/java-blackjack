package domain;

import java.util.regex.Pattern;

public record PlayerName(String username) {
    private static final Pattern INPUT_PATTERN = Pattern.compile("^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z ]+$");

    public PlayerName {
        validateEmptyAndBlankName(username);
        validateUserName(username);
    }

    private void validateEmptyAndBlankName(String username) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }

    public static void validateUserName(String names) {
        if (!INPUT_PATTERN.matcher(names).matches()) {
            throw new IllegalArgumentException("이름은 한글과 영문만 가능합니다.");
        }
    }
}
