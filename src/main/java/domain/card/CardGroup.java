package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                .mapToInt(Card::getScore)
                .sum();
    }

    private int calculateScoreWithAce(int sum, int limit) {
        int aceCount = countAce();
        sum += aceCount;
        while (aceCount-- > 0) {
            int temp = sum + 10;
            if (temp > limit) {
                break;
            }
            sum = temp;
        }

        return sum;
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
        return calculateScoreWithAce(calculateScoreWithOutAce(), limit);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
