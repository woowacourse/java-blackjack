package blackjack.domain.card.strategy;

import java.util.LinkedList;
import java.util.List;

import blackjack.domain.card.Card;

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
