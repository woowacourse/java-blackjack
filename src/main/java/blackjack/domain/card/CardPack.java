package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardPack {

    private final List<Card> cards;

    private CardPack(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getDealCards(int count) {
        List<Card> dealCards = new ArrayList<>();
        IntStream.range(0, count).forEach(i ->
                dealCards.add(cards.removeLast()));
        return Collections.unmodifiableList(dealCards);
    }

    public static CardPack createShuffled() {
        List<Card> shuffledCards = createCards();
        Collections.shuffle(shuffledCards);
        return new CardPack(shuffledCards);
    }

    private static List<Card> createCards() {
        return Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(number, shape)))
                .collect(Collectors.toList());
    }
}
