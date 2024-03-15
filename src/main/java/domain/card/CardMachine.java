package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class CardMachine {

    private static final int DEFAULT_NUMBER_OF_DECKS = 6;

    private CardMachine() {}

    public static Deck newDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < DEFAULT_NUMBER_OF_DECKS; i++) {
            deck.addAll(createCardDeck());
        }
        return new Deck(deck);
    }

    private static List<Card> createCardDeck() {
        return Arrays.stream(Suit.values())
                .flatMap(CardMachine::addCard)
                .toList();
    }

    private static Stream<Card> addCard(final Suit suit) {
        return EnumSet.allOf(Rank.class)
                .stream()
                .map(cardNumber -> new Card(suit, cardNumber));
    }
}
