package blackjack.domain.Cards;

import blackjack.domain.participants.Players;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Hand {

    private static final int ACE_BONUS_SCORE = 10;
    private static final int NO_BONUS_SCORE = 0;

    private final List<Card> cards;

    public Hand() {
        this.cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
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

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
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
        return Collections.unmodifiableList(cards);
    }
}
