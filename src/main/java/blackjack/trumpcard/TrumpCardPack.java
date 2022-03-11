package blackjack.trumpcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TrumpCardPack {
    private final List<TrumpCard> values;

    public TrumpCardPack() {
        this.values = createCards();
    }

    private List<TrumpCard> createCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();
        Arrays.stream(TrumpSymbol.values())
                .map(this::createCardsOfSymbol)
                .forEach(trumpCards::addAll);
        return trumpCards;
    }

    private List<TrumpCard> createCardsOfSymbol(TrumpSymbol trumpSymbol) {
        return Arrays.stream(TrumpNumber.values())
                .map(trumpNumber -> new TrumpCard(trumpNumber, trumpSymbol))
                .collect(Collectors.toList());
    }

    public TrumpCard draw() {
        Collections.shuffle(values);

        final int topCardIndex = 0;
        TrumpCard topCard = values.get(topCardIndex);
        values.remove(topCardIndex);
        return topCard;
    }
}
