package blackjack.domain.participant;

import static blackjack.util.constant.Constants.MAX_LENGTH;
import static blackjack.util.constant.ErrorMessage.USER_NAME_EMPTY;
import static blackjack.util.constant.ErrorMessage.USER_NAME_LENGTH;

public record UserName(
        String name
) {

    public UserName {
        validate(name);
        name = name.strip();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(USER_NAME_EMPTY);
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(USER_NAME_LENGTH);
        }
    }
}
