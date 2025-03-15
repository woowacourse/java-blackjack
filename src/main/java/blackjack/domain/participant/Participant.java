package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.HandCards;
import java.util.List;

public abstract class Participant {

    protected final HandCards handCards;

    public Participant() {
        this.handCards = new HandCards();
    }

    public void addInitialCards(final CardDeck cardDeck) {
        addCards(
                cardDeck.pickRandomCard(),
                cardDeck.pickRandomCard()
        );
    }

    public void addCards(final Card... cards) {
        for (Card card : cards) {
            this.handCards.addCard(card);
        }
    }

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }

    public boolean isBust() {
        return handCards.isBust();
    }

    public int calculateDenominations() {
        return handCards.calculateDenominations();
    }

    public List<Card> openCards() {
        return handCards.getCards();
    }

    public abstract List<Card> openInitialCards();

    public abstract boolean isPossibleToAdd();
}
