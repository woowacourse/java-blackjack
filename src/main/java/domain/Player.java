package domain;

import view.dto.PlayerDto;

public class Player extends Participant {

    public Player(final Name name) {
        super(name);
    }

    public Player(final PlayerDto playerDto) {
        this(playerDto.name());
    }

    public boolean isBust() {
        return hand.sum() > 21;
    }

    public boolean isBlackjack() {
        return hand.sum() == 21;
    }
}
