package blackjack.domain.card.generator;

import java.util.LinkedList;
import java.util.List;

import blackjack.domain.card.Card;

public class ManualDeckGenerator implements DeckGenerator {

    private List<Card> cards;

    public void initCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> generate() {
        return new LinkedList<>(cards);
    }

}
