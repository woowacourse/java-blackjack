package blackjack.domain.deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM  = new Random();

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

    public int calculateTotalScore() {
        validateDeck();
        int totalScore = 0;
        for (Card card : cards) {
            Rank rank = card.getRank();
            totalScore += rank.getScore(totalScore);
        }
        return totalScore;
    }

    private void validateDeck() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
