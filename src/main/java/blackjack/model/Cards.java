package blackjack.model;

import static blackjack.model.Score.ACE;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int ADDITIONAL_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateSize(final List<Card> cards) {
        if (cards.size() < 2) {
            throw new IllegalArgumentException("카드는 두 장 이상이어야 합니다.");
        }
    }

    public Cards addCard(final Card card) {
        cards.add(card);
        return new Cards(cards);
    }

    public int calculateScore() {
        int totalScoreWithoutAce = calculateBaseScore();
        int aceCount = countAce();

        if (isSoft(aceCount, totalScoreWithoutAce)) {
            totalScoreWithoutAce += aceCount * ADDITIONAL_ACE_SCORE;
        }

        return totalScoreWithoutAce + aceCount * ACE.getValue();
    }

    private int calculateBaseScore() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean isSoft(final int aceCount, final int baseScore) {
        return aceCount == 1 && baseScore <= 10;
    }

    public boolean isBust(final int threshold) {
        return calculateScore() > threshold;
    }


    public int countSize() {
        return cards.size();
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateScore() == 21;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
