package domain.participant;

import constant.PolicyConstant;
import exception.ErrorMessage;
import java.util.List;
import util.Parser;

public record Names(
    List<Name> value
) {

    public Names {
        validate(value);
    }

    private void validate(List<Name> value) {
        validateNameDuplicated(value);
        validateNameCountOutOfRange(value);
    }

    private void validateNameDuplicated(List<Name> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_DUPLICATED.getMessage());
        }
    }

    private void validateNameCountOutOfRange(List<Name> names) {
        if (!(PolicyConstant.PLAYER_MIN_COUNT <= names.size() && names.size() <= PolicyConstant.PLAYER_MAX_COUNT)) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }

    public static Names from(String input) {
        List<String> parsedInput = Parser.parseInput(input, PolicyConstant.DELIMITER);
        List<Name> names = parsedInput.stream()
            .map(Name::new)
            .toList();
        return new Names(names);
    }
}
