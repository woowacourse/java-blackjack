package blackjack.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticipantName {

    public static final String PLAYER_NAME_ERROR_MESSAGE = "플레이어의 이름은 숫자,영어,한글만 가능합니다.";
    private static final Pattern pattern = Pattern.compile("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$");
    public static final String NULL_OR_BLANK_ERROR_MESSAGE = "null 또는 빈칸이 들어올 수 없습니다.";

    private final String name;

    public ParticipantName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        checkNull(name);
        checkName(name);
    }

    private void checkName(String name) {
        Matcher nameMatcher = pattern.matcher(name);
        if (!nameMatcher.matches()) {
            throw new IllegalArgumentException(PLAYER_NAME_ERROR_MESSAGE);
        }
    }

    private void checkNull(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_BLANK_ERROR_MESSAGE);
        }
    }
}
