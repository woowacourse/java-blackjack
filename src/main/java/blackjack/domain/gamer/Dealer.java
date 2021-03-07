package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Cards;

public class Dealer extends Participant {
    public static final Name DEALER_NAME = new Name("딜러");
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super(DEALER_NAME, new Cards());
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(player.cards.calculateScore(), cards.calculateScore());
    }

    @Override
    public boolean canDraw() {
        return this.cards.calculateScore() <= DEALER_DRAW_CONDITION;
    }
}

