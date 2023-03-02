package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }


    public int getSum() {
        Integer sum = cards.stream()
                .map(s -> s.getCardValue())
                .collect(Collectors.summingInt(Integer::intValue));

        return sum;
    }

    public boolean isExistAce() {
        return cards.stream()
                .anyMatch(s -> s.getCardValue() == CardNumber.ACE.getValue());
    }
}
