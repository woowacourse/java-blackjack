package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardPack {

    private final List<Card> cards;

    public CardPack() {
        this.cards = initCards();
        Collections.shuffle(this.cards);
    }

    private List<Card> initCards() {
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(number, shape)))
                .collect(Collectors.toList());
    }

    public List<Card> getDealCards(int count) {
        List<Card> dealCards = new ArrayList<>();
        IntStream.range(0, count).forEach(i ->
                dealCards.add(cards.removeLast()));
        return dealCards;
    }
}
