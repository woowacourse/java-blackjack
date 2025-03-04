package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackDeckGenerator {

    public Set<TrumpCard> generateDeck() {
        List<Suit> suits = Arrays.stream(Suit.values()).toList();
        Set<TrumpCard> trumpCards = new HashSet<>();
        for (Suit suit : suits) {
            trumpCards.addAll(addTrumpCards(suit));
        }
        return trumpCards;
    }

    private Set<TrumpCard> addTrumpCards(Suit suit) {
        List<CardValue> cardValues = CardValue.cardValues();
        return cardValues.stream().map(cardValue -> new TrumpCard(suit, cardValue))
                .collect(Collectors.toSet());
    }
}
