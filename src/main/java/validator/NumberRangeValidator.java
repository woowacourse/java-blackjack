package validator;

import exception.InvalidInputException;
import exception.OutOfRangeException;

public class NumberRangeValidator implements Validator {
    private final long startInclusive;
    private final long endInclusive;

    public NumberRangeValidator(long startInclusive, long endExclusive) {
        this.startInclusive = startInclusive;
        this.endInclusive = endExclusive;
    }

    public NumberRangeValidator() {
        this(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    @Override
    public void validate(String input) {
        try {
            long parsedValue = Long.parseLong(input.strip());
            validateRange(parsedValue);
        } catch (NumberFormatException ne) {
            throw new InvalidInputException();
        }
    }

    private void validateRange(long parsedValue) {
        if (parsedValue < startInclusive || parsedValue > endInclusive) {
            throw new OutOfRangeException(
                    String.format("(%d~%d) 범위의 정수만 입력 가능합니다",
                            startInclusive,
                            endInclusive));
        }
    }
}
