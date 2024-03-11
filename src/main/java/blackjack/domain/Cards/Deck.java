package blackjack.domain.Cards;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final Random RANDOM = new Random();
    private static final int ACE_BONUS_SCORE = 10;
    private static final int NO_BONUS_SCORE = 0;

    private final List<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card pickRandomCard() {
        validateDeck();
        int pickCardIndex = RANDOM.nextInt(cards.size());
        return cards.remove(pickCardIndex);
    }

    private void validateDeck() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
    }

    public int size() {
        return cards.size();
    }
}
