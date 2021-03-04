package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Cards;

public class Dealer extends Person {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super.name = DEALER_NAME;
        super.cards = new Cards();
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(player.cards.calculateScore(), cards.calculateScore());
    }

    @Override
    public boolean canDraw() {
        return this.cards.calculateScore() <= DEALER_DRAW_CONDITION;
    }
}

