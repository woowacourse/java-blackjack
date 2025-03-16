package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.GameManager.BLACKJACK_NUMBER;
import static domain.card.Card.ACE_HIGH_SCORE;
import static domain.card.Card.ACE_LOW_SCORE;

public class CardGroup {

    private final List<Card> cards;

    public CardGroup() {
        cards = new ArrayList<>();
    }

    public CardGroup(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    private int calculateScoreWithOutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(card -> card.getScore().getValue())
                .sum();
    }

    private int calculateScoreWithAce(int sum, int aceCount) {
        final int upperAceScoreCount = availableUpperAceScoreCount(sum, aceCount);
        return sum + ACE_HIGH_SCORE * upperAceScoreCount + (aceCount - upperAceScoreCount) * ACE_LOW_SCORE;
    }

    private int availableUpperAceScoreCount(int sum, int aceCount) {
        sum += aceCount * ACE_LOW_SCORE;
        int aceAdditionScore = ACE_HIGH_SCORE - ACE_LOW_SCORE;
        int count = 0;
        while (!isOverHitRule(sum + aceAdditionScore) && count < aceCount) {
            count++;
            sum += aceAdditionScore;
        }
        return count;
    }

    private boolean isOverHitRule(int score) {
        return score > BLACKJACK_NUMBER;
    }

    public int calculateScore() {
        return calculateScoreWithAce(calculateScoreWithOutAce(), countAce());
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int countCards() {
        return cards.size();
    }

    private int countAce() {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce)
                .count());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
