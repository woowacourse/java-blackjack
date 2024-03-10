package blackjack.domain.deck;

import blackjack.domain.participants.Players;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM  = new Random();
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

    public int calculateTotalScore() {
        validateDeck();
        int middleScore = calculateMiddleScore();
        return middleScore + addBonusScore(middleScore);
    }

    private int calculateMiddleScore() {
        return cards.stream()
                .map(Card::getRank)
                .mapToInt(Rank::getScore)
                .sum();
    }

    private int addBonusScore(int middleScore) {
        if (hasAce() && canAddAceBonusScore(middleScore)) {
            return ACE_BONUS_SCORE;
        }
        return NO_BONUS_SCORE;
    }

    private static boolean canAddAceBonusScore(int totalScore) {
        return totalScore + ACE_BONUS_SCORE <= Players.MAX_SCORE;
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
