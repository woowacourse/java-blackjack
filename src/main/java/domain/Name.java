package domain;

import static constant.BlackjackConstant.NAME_MAX_LENGTH;
import static constant.BlackjackConstant.NAME_MINIMUM_LENGTH;
import static exception.ErrorMessage.PLAYER_NAME_LENGTH_ERROR;

public record Name(String name) {

    public Name {
        if (name.length() < NAME_MINIMUM_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH_ERROR.getMessage());
        }
    }
}
