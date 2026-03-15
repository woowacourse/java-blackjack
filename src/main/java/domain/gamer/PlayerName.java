package domain.gamer;

import domain.gamer.exception.ErrorMessage;
import domain.gamer.exception.PlayerException;

public record PlayerName(
        String name
) {

    private static final int NAME_LENGTH_CONDITION = 5;

    public PlayerName{
        validateNameIsBlank(name);
        validateNameLength(name);
    }

    private static void validateNameIsBlank(String name) {
        if (name.isBlank()) {
            throw new PlayerException(ErrorMessage.NAME_BLANK_ERROR);
        }
    }

    private static void validateNameLength(String name) {
        if(name.length() > NAME_LENGTH_CONDITION ) {
            throw new PlayerException(ErrorMessage.NAME_LENGTH_ERROR);
        }
    }

}
