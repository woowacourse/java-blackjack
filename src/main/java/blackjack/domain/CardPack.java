package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardPack {

    private final List<Card> cards;

    public CardPack() {
        List<Card> cards = new ArrayList<>();

        Arrays.stream(CardShape.values())
                .forEach(shape -> {
                    Arrays.stream(CardNumber.values())
                            .forEach(number -> {
                                cards.add(new Card(number, shape));
                            });
                });

        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
