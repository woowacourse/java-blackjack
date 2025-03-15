package domain.gamer;

import static domain.BlackJackConstants.THRESHOLD;

import domain.calculatestrategy.DealerStrategy;
import domain.deck.Card;
import java.util.List;

public class Dealer extends Gamer {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Nickname(DEALER_NAME), new DealerStrategy());
    }

    public boolean canHit() {
        return getSumOfRank() <= THRESHOLD;
    }

    public Card getFirstCard() {
        final List<Card> cards = getCards();
        return cards.getFirst();
    }
}
