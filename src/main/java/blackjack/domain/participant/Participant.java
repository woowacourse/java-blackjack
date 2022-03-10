package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    protected String name;
    protected List<Card> cards = new ArrayList<>();

    public void drawCard(final Deck deck) {
        cards.add(deck.drawCard());
    }

    protected int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public boolean isBurst() {
        return calculateScore() > 21;
    }

    public boolean isHigherThan(Participant other) {
        final int thisScore = this.calculateScore();
        final int otherScore = other.calculateScore();
        return thisScore > otherScore;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        return calculateScore();
    }

}
