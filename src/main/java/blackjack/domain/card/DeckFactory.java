package blackjack.domain.card;

import java.util.Arrays;
import java.util.Stack;

public class DeckFactory {

    public static Deck createDeck(DeckType deckType) {
        if (deckType == DeckType.BLACKJACK) {
            return createBlackJackDeck();
        }
        return null;
    }

    private static Deck createBlackJackDeck() {
        Stack<Card> cards = new Stack<>();

        Arrays.stream(CardNumber.values())
                .forEach(cardNumber -> Arrays.stream(CardSymbol.values())
                        .forEach(cardSymbol -> cards.add(new Card(cardNumber, cardSymbol))));

        return new Deck(cards);
    }

}
