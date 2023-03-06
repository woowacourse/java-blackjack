package blackjack.model.state;

import blackjack.model.ResultState;
import blackjack.model.card.HandCard;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;

import java.util.List;

public abstract class State {
    protected static final int BLACKJACK_NUMBER = 21;
    private static final int FIRST_DISTRIBUTE_COUNT = 2;

    protected final HandCard handCard;

    public State(HandCard handCard) {
        this.handCard = handCard;
    }

    public abstract State draw(CardDeck cardDeck);

    public abstract boolean isFinished();

    public abstract boolean isBlackjack();

    public abstract boolean isBust();

    public abstract boolean isStand();

    public boolean isCardDistributed() {
        return (handCard.size() >= FIRST_DISTRIBUTE_COUNT);
    }

    public CardScore getScore(ResultState state) {
        return handCard.score(state);
    }

    public List<Card> getHand() {
        return handCard.getCards();
    }
}
