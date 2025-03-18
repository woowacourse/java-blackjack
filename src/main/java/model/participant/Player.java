package model.participant;

import exception.IllegalBlackjackInputException;

public record Player(String name) {

    public Player {
        validate(name);
    }

    private void validate(final String name) {
        validateNullOrEmpty(name);
        validateNameLength(name);
    }

    private void validateNullOrEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalBlackjackInputException("플레이어의 이름은 공백이나 null일 수 없습니다.");
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > 10) {
            throw new IllegalBlackjackInputException("플레이어의 이름은 1자 이상 10자 이하여야합니다.");
        }
    }
}
