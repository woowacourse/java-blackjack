package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hands {

    private static final int ACE_BONUS_SCORE = 10;
    private static final int NO_BONUS_SCORE = 0;

    private final List<Card> cards;

    public Hands(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateScore() {
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

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void receiveHands(Hands newHands) {
        cards.clear();
        cards.addAll(newHands.cards);
    }

    private boolean canAddAceBonusScore(int middleScore) {
        return middleScore + ACE_BONUS_SCORE <= Player.MAX_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
