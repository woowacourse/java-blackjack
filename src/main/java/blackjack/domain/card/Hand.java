package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.game.GameRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card newCard) {
        cards.add(newCard);
    }

    public void addCard(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public int calculateTotalPoint() {
        int sumWithoutAce = calculatePointSumWithoutAce();
        int aceCount = calculateAceCount();
        return calculateTotalPoint(sumWithoutAce, aceCount);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private int calculatePointSumWithoutAce() {
        return cards.stream()
                .filter(card -> !card.getValue().equals(CardValue.ACE))
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getValue().equals(CardValue.ACE))
                .count();
    }

    private int calculateTotalPoint(int sumWithoutAce, int aceCount) {
        int total = sumWithoutAce;
        for (int i = 0; i < aceCount; i++) {
            total += decideAceCardPoint(total);
        }
        return total;
    }

    private int decideAceCardPoint(int currentTotalPoint) {
        if (GameRule.isBust(currentTotalPoint + GameRule.SOFT_ACE.getValue())) {
            return CardValue.ACE.getPoint();
        }
        return GameRule.SOFT_ACE.getValue();
    }
}
