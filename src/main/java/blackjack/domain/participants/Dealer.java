package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.Objects;

public class Dealer {

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public void prepareCards(Deck deck) {
        cards.take(deck.draw(), deck.draw());
    }

    public void drawAdditionalCard(Deck deck) {
        while (calculateMaxScore() <= 16) {
            cards.take(deck.draw());
        }
    }

    public int calculateMaxScore() {
        return cards.calculateMaxScore();
    }

    public Cards getCards() {
        return cards;
    }

    public int getCardSize() {
        return cards.getSize();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Dealer dealer = (Dealer) object;
        return Objects.equals(getCards(), dealer.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCards());
    }
}
