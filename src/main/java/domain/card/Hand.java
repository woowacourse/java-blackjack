package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void receive(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void receive(List<Card> receivedCards) {
        cards.addAll(receivedCards);
    }

    public Score totalScore() {
        return Score.totalScoreOf(this);
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(card -> card.rank() == Rank.ACE);
    }

    public List<Card> getCards() {
        return cards;
    }
}
