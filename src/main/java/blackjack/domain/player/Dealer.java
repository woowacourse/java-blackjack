package blackjack.domain.player;

import blackjack.domain.player.Player;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final int BOUNDARY_SCORE = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= BOUNDARY_SCORE;
    }
}
