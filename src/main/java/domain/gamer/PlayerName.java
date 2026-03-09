package domain.gamer;

import domain.common.exception.BlackjackGameException;
import domain.gamer.exception.ErrorMessage;
import domain.gamer.exception.PlayerException;

public record PlayerName(
        String name
) {

    public static PlayerName from(String name) {
        validateNameIsBlank(name);
        validateNameLength(name);
        return new PlayerName(name);
    }

    private static void validateNameIsBlank(String name) {
        if (name.isBlank()) {
            throw new BlackjackGameException(ErrorMessage.NAME_BLANK_ERROR);
        }
    }

    private static void validateNameLength(String name) {
        if(name.length() > 5) {
            throw new PlayerException(ErrorMessage.NAME_LENGTH_ERROR);
        }
    }

}
