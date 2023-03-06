package blackjackgame.domain.user;

import blackjackgame.view.ErrorMessage;

public class Name {
    private final String name;

    public Name(String name) {
        validateName(name);

        this.name = name;
    }

    private void validateName(String name) {
        if (name.isBlank() || name.equals("딜러")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYER_NAME_FORMAT.getMessage());
        }
    }

    public String getName() {
        return name;
    }
}
