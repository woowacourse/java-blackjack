package domain.blackjackgame;

import domain.strategy.DrawStrategy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackDeckGenerator {

    public static BlackjackDeck generateDeck(DrawStrategy drawStrategy) {
        List<Suit> suits = Arrays.stream(Suit.values()).toList();
        Set<TrumpCard> trumpCards = new HashSet<>();
        for (Suit suit : suits) {
            trumpCards.addAll(trumpCards(suit));
        }
        LinkedList<TrumpCard> generatedDeck = new LinkedList<>(trumpCards);
        Collections.shuffle(generatedDeck);

        return new BlackjackDeck(generatedDeck, drawStrategy);
    }

    private static Set<TrumpCard> trumpCards(Suit suit) {
        List<CardValue> cardValues = CardValue.cardValues();
        return cardValues.stream().map(cardValue -> new TrumpCard(suit, cardValue))
                .collect(Collectors.toSet());
    }
}
