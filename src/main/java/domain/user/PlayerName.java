package domain.user;

import java.util.ArrayList;
import java.util.List;

public final class PlayerName implements UserName {
    private static final List<String> PROHIBITED_NAMES = new ArrayList<>() {{
        add("딜러");
    }};
    private static final int MAXIMUM_NAME_LENGTH = 100;

    private final String name;

    public PlayerName(String nameValue) {
        validate(nameValue);
        this.name = nameValue;
    }

    private void validate(String nameValue) {
        validateNullAndBlank(nameValue);
        validateWrongName(nameValue);
        validateLength(nameValue);
    }

    private static void validateNullAndBlank(String nameValue) {
        if (nameValue == null || nameValue.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이나 빈 값이 들어갈 수 없습니다.");
        }
    }

    private void validateWrongName(String nameValue) {
        if (PROHIBITED_NAMES.contains(nameValue)) {
            throw new IllegalArgumentException("사용이 금지된 이름입니다.");
        }
    }

    private void validateLength(String nameValue) {
        if (nameValue.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("너무 긴 이름입니다.");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
