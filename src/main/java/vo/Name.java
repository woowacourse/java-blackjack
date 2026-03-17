package vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣, ]+$");

    private final String name;

    public Name(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    private void validateName(String name) {
        validateParticipantEmptyInput(name);
        validateNonLiteralInput(name);
        validateInvalidSymbolInput(name);
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
