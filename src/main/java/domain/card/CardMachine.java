package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class CardMachine {

    private static final int DEFAULT_NUMBER_OF_DECKS = 6;

    private CardMachine() {}

    public static Cards cardDecks() {
        List<Card> cardDecks = new ArrayList<>();
        for (int i = 0; i < DEFAULT_NUMBER_OF_DECKS; i++) {
            cardDecks.addAll(createCardDeck());
        }
        return Cards.from(cardDecks);
    }

    private static List<Card> createCardDeck() {
        return Arrays.stream(CardSuit.values())
                .flatMap(CardMachine::addCard)
                .toList();
    }

    private static Stream<Card> addCard(final CardSuit cardSuit) {
        return EnumSet.allOf(CardRank.class)
                .stream()
                .map(cardNumber -> new Card(cardSuit, cardNumber));
    }
}
