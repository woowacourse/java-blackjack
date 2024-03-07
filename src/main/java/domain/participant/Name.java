package domain.participant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {

    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern HANGUL_ALPHABET_NUMBER = Pattern.compile("^[가-힣a-zA-Z0-9]*$");

    private final String name;

    public Name(String rawName) {
        validate(rawName);
        this.name = rawName;
    }

    private void validate(String rawName) {
        validateNull(rawName);
        validateLength(rawName);
        validateCharacter(rawName);
    }

    private void validateNull(String rawName) {
        if (rawName == null) {
            throw new IllegalArgumentException("[ERROR] null을 입력할 수 없습니다.");
        }
    }

    private void validateLength(String rawName) {
        if (rawName.isEmpty() || rawName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 1~5자의 이름만 입력 가능합니다.");
        }
    }

    private void validateCharacter(String rawName) {
        Matcher matcher = HANGUL_ALPHABET_NUMBER.matcher(rawName);
        if (matcher.matches()) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 한글, 영어, 숫자만 입력 가능합니다.");
    }

    public String getName() {
        return name;
    }
}
