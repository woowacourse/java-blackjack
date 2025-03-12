package blackjack.domain.participants;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.Objects;

public class Dealer {

    private final Cards cards;
    private int amount;

    public Dealer(Cards cards) {
        this(cards, 0);
    }

    public Dealer(Cards cards, int amount) {
        this.cards = cards;
        this.amount = amount;
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

    public void plusAmount(int amount) {
        this.amount += amount;
    }

    public void minusAmount(int amount) {
        this.amount -= amount;
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
        return amount == dealer.amount && Objects.equals(getCards(), dealer.getCards());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCards());
        result = 31 * result + amount;
        return result;
    }
}
