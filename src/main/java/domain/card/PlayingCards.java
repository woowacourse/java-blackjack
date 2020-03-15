package domain.card;

import domain.Score;

import java.util.Collections;
import java.util.List;

import static domain.card.Deck.INIT_CARDS_SIZE;

public class PlayingCards {
    private List<Card> cards;

    public PlayingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        return Score.valueOf(cards);
    }

    public int countCards() {
        return cards.size();
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isNotBust() {
        return calculateScore().isNotBust();
    }

    public boolean isBlackJack() {
        return countCards() == INIT_CARDS_SIZE && calculateScore().isBlackJackScore();
    }

    public boolean isNotBlackJack() {
        return countCards() != INIT_CARDS_SIZE || calculateScore().isNotBlackJackScore();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
