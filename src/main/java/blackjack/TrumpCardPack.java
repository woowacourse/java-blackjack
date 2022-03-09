package blackjack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TrumpCardPack {
    private final Set<TrumpCard> values;

    public TrumpCardPack() {
        this.values = createCardsOfSymbol();
    }

    private Set<TrumpCard> createCardsOfSymbol() {
        Set<TrumpCard> trumpCards = new HashSet<>();
        Arrays.stream(TrumpSymbol.values())
                .map(this::createCardsOfSymbol)
                .forEach(trumpCards::addAll);
        return trumpCards;
    }

    private Set<TrumpCard> createCardsOfSymbol(TrumpSymbol trumpSymbol) {
        return Arrays.stream(TrumpNumber.values())
                .map(trumpNumber -> new TrumpCard(trumpSymbol, trumpNumber))
                .collect(Collectors.toSet());
    }
}
