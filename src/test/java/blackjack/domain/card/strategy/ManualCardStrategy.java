package blackjack.domain.card.strategy;

import blackjack.domain.card.Card;

import java.util.LinkedList;
import java.util.List;

public class ManualCardStrategy implements CardStrategy {

    private List<Card> cards;

    public void initCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> generate() {
        return new LinkedList<>(cards);
    }

}
