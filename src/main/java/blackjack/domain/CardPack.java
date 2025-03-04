package blackjack.domain;

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
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(number, shape)))
                .toList();
    }
}
