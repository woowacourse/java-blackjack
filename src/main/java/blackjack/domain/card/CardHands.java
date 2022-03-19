package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardHands {

    private final List<Card> cards;

    public CardHands() {
        this.cards = new ArrayList<>();
    }

    public CardHands(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return ScoreCalculator.calculateScore(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
