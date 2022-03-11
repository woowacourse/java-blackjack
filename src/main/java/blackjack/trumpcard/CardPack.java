package blackjack.trumpcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardPack {
    private final List<Card> values;

    public CardPack() {
        this.values = createCards();
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(TrumpSymbol.values())
                .map(this::createCardsOfSymbol)
                .forEach(cards::addAll);
        return cards;
    }

    private List<Card> createCardsOfSymbol(TrumpSymbol trumpSymbol) {
        return Arrays.stream(TrumpNumber.values())
                .map(trumpNumber -> new Card(trumpNumber, trumpSymbol))
                .collect(Collectors.toList());
    }

    public Card draw() {
        Collections.shuffle(values);

        final int topCardIndex = 0;
        Card topCard = values.get(topCardIndex);
        values.remove(topCardIndex);
        return topCard;
    }
}
