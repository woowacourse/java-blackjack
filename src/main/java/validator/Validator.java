package validator;

import java.util.Set;

public interface Validator {

    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
    }

    static void validateChoice(String input) {
        final String YES_COMMAND = "y";
        final String NO_COMMAND = "n";

        if (!Set.of(YES_COMMAND, NO_COMMAND).contains(input.strip())) {
            throw new IllegalArgumentException("[ERROR] 입력이 올바르지 않습니다.");
        }
    }

    void validate(String input);

}

