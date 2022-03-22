package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Gamer {
    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 17;
    private static final int CARD_INDEX = 0;

    public Dealer(Cards cards) {
        super(cards, new Name(DEALER_NAME));
    }

    public Dealer() {
        this(new Cards());
    }

    @Override
    public boolean isWin(Gamer gamer) {
        if (isBlackJackWin(gamer) || (isNotBustBoth(gamer) && isHigherScore(gamer))) {
            return true;
        }
        return gamer.isBust();
    }

    public Card getRandomOneCard() {
        return this.cards.getCards().get(CARD_INDEX);
    }

    @Override
    public boolean canHit() {
        return this.getScore() < HIT_THRESHOLD;
    }
}
