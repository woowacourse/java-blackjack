package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards initialize() {
        return new Cards(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(List<Card> drawnCards) {
        cards.addAll(drawnCards);
    }

    public int calculateSum() {
        long aceCount = cards.stream()
                .filter(card -> card.getValue().equals(CardValue.ACE))
                .count();

        int sumWithoutAce = cards.stream()
                .filter(card -> !card.getValue().equals(CardValue.ACE))
                .mapToInt(Card::getPoint)
                .sum();

        for (int i = 0; i < aceCount; i++) {
            if(GameRule.isBurst(sumWithoutAce + GameRule.SOFT_ACE.getValue())) {
                sumWithoutAce += CardValue.ACE.getPoint();
            }
            sumWithoutAce += GameRule.SOFT_ACE.getValue();
        }

        return sumWithoutAce;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
