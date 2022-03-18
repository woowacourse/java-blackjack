package blackjack.domain.player;

import blackjack.domain.result.Score;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canAddCard() {
        return new Score(this).CanAddDealerCard();
    }
}
