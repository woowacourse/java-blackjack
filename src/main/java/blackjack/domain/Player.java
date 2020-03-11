package blackjack.domain;

import blackjack.exceptions.InvalidPlayerException;

public class Player {
    private final Hand hand;
    private final String name;
    private Result result;

    public Player(final String name) {
        validate(name);
        this.name = name;
        this.hand = new Hand();
    }

    private void validate(final String name) {
        if (isNullOrEmpty(name)) {
            throw new InvalidPlayerException("빈 칸 또는 null 값이 들어올 수 없습니다.");
        }
    }

    private boolean isNullOrEmpty(final String name) {
        return name == null || name.trim().isEmpty();
    }

}
