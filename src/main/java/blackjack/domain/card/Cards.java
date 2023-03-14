package blackjack.domain.card;

import blackjack.util.CardPickerGenerator;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int MAX_DECK_SIZE = 6;
    private final List<Card> cards;
    private final CardPickerGenerator cardPickerGenerator;

    private Cards(List<Card> cards, CardPickerGenerator cardPickerGenerator) {
        this.cards = cards;
        this.cardPickerGenerator = cardPickerGenerator;
    }

    public static Cards generator(CardPickerGenerator cardPickerGenerator) {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < MAX_DECK_SIZE; i++) {
            settingOneDeck(deck);
        }
        return new Cards(deck, cardPickerGenerator);
    }

    private static void settingOneDeck(List<Card> deck) {
        for (Card card : Card.cache) {
            deck.add(card);
        }
    }

    public Card pick() {
        return cards.remove(cardPickerGenerator.generator(cards.size()));
    }
}
