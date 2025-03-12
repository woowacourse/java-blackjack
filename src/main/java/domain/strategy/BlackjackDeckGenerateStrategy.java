package domain.strategy;

import domain.blackjackgame.BlackjackDeck;
import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackDeckGenerateStrategy implements DeckGenerateStrategy {

    @Override
    public BlackjackDeck generateDeck(DrawStrategy drawStrategy) {
        List<Suit> suits = Arrays.stream(Suit.values()).toList();
        Set<TrumpCard> trumpCards = new HashSet<>();
        for (Suit suit : suits) {
            trumpCards.addAll(trumpCards(suit));
        }
        List<TrumpCard> deckBeforeShuffle = new LinkedList<>(trumpCards);
        Collections.shuffle(deckBeforeShuffle);
        ArrayDeque<TrumpCard> generatedDeck = new ArrayDeque<>(deckBeforeShuffle);
        return new BlackjackDeck(generatedDeck, drawStrategy);
    }

    private Set<TrumpCard> trumpCards(Suit suit) {
        List<CardValue> cardValues = CardValue.cardValues();
        return cardValues.stream().map(cardValue -> new TrumpCard(suit, cardValue))
                .collect(Collectors.toSet());
    }
}
