package exception;

import domain.gamer.Name;

public class NameLengthException extends RuntimeException {
    public static final String INVALID_NAME_LENGTH = String.format("이름은 %d글자 이상, %d글자 이하 이어야 합니다.",
            Name.MINIMUM_NAME_LENGTH, Name.MAXIMUM_NAME_LENGTH);

    public NameLengthException(final String message) {
        super(message);
    }
}
