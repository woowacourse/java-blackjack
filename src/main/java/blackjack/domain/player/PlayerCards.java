package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public static PlayerCards createEmptyCards() {
        return new PlayerCards(new ArrayList<>());
    }

    public void append(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        return new Score(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
