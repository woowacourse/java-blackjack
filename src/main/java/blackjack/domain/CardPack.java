package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    public CardPack() {
        this.cards = initCards();
    }

    public List<Card> getCards() {
        return cards;
    }

    private List<Card> initCards() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardShape.values())
                .forEach(shape -> makeCard(shape, cards));
        return cards;
    }

    private void makeCard(final CardShape shape, final List<Card> cards) {
        Arrays.stream(CardNumber.values())
                .forEach(number -> {
                    cards.add(new Card(number, shape));
                });
    }
}
