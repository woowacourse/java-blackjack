package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM  = new Random();
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card pickRandomCard() {
        validateDeck();
        int cardIndex = RANDOM.nextInt(cards.size());
        return cards.remove(cardIndex);
    }

    public int calculateTotalScore() {
        validateDeck();
        int totalScore = cards.stream()
                .map(Card::getRank)
                .mapToInt(Rank::getScore)
                .sum();
        if (hasAce() && totalScore + ACE_BONUS_SCORE <= Participants.MAX_BOUNDARY_SCORE) {
            totalScore += ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private void validateDeck() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 없습니다.");
        }
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
