package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGroup {

    private static final int ACE_DISTANCE_SCORE = 10;
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

    private int calculateScoreWithAce(int sum, int limit, int aceCount) {
        final int result = sum + aceCount;
        return result + addScoreWithAce(0, limit - result, aceCount);
    }

    private int addScoreWithAce(int sum, int limit, int aceCount) {
        if (aceCount <= 0 || sum + ACE_DISTANCE_SCORE > limit) {
            return sum;
        }
        return addScoreWithAce(sum + ACE_DISTANCE_SCORE, limit, aceCount - 1);
    }

    public boolean addCard(final Card card) {
        final int size = cards.size();
        cards.add(card);
        return size + 1 == cards.size();
    }

    public int countCards() {
        return cards.size();
    }

    private int countAce() {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce)
                .count());
    }

    public int calculateScore(int limit) {
        return calculateScoreWithAce(calculateScoreWithOutAce(), limit, countAce());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
