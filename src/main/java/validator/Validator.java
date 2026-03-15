package validator;

import constant.CommandConstant;
import exception.BlankInputException;
import exception.InvalidInputException;

import java.util.Set;

public interface Validator {
    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new BlankInputException();
        }
    }

    static void validateChoice(String input) {
        if (hasChoiceCommand(input)) {
            throw new InvalidInputException();
        }
    }

    private static boolean hasChoiceCommand(String input) {
        return !Set.of(CommandConstant.YES_COMMAND, CommandConstant.NO_COMMAND).contains(input.strip().toLowerCase());
    }

    void validate(String input);
}
