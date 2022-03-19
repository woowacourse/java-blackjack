package blackjack.domain.player;

import java.util.Arrays;
import java.util.List;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

public class Player extends Participant {

    public Player(Name name, HoldCards holdCards) {
        super(name, holdCards);
    }

    public Score compete(Dealer dealer) {
        return Arrays.stream(Score.values())
            .filter(score -> score.match(this, dealer))
            .findAny().orElse(Score.LOSE);
    }

    @Override
    public boolean canHit() {
        return holdCards.getOptimizeTotalNumber() <= BLACKJACK_NUMBER;
    }

    @Override
    public List<Card> openCards() {
        return getHoldCards();
    }

    @Override
    public Player copy() {
        return new Player(name, holdCards.copy());
    }
}
