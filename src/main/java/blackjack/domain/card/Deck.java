package blackjack.domain.card;

import blackjack.util.CardPickerGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private static final int MAX_DECK_SIZE = 6;
    private final List<Card> cards;
    private final CardPickerGenerator cardPickerGenerator;

    private Deck(final List<Card> cards, final CardPickerGenerator cardPickerGenerator) {
        this.cards = cards;
        this.cardPickerGenerator = cardPickerGenerator;
    }

    public static Deck create(final CardPickerGenerator cardPickerGenerator) {
        List<Card> deck = new ArrayList<>();
        for (int count = 0; count < MAX_DECK_SIZE; count++) {
            deck.addAll(initCards());
        }
        return new Deck(deck, cardPickerGenerator);
    }

    private static List<Card> initCards() {
        return Arrays.stream(CardSuit.values())
                .flatMap(cardSuit -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> (new Card(cardNumber, cardSuit))))
                .collect(Collectors.toList());

    }

    public Card pick() {
        return cards.remove(cardPickerGenerator.generator(cards.size()));
    }
}
