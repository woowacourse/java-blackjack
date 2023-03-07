package blackjack.domain.card;

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
        for (CardNumber cardNumber : CardNumber.values()) {
            generateCardByAllSymbol(cards, cardNumber);
        }
        return new Deck(cards);
    }

    private static void generateCardByAllSymbol(Stack<Card> cards, CardNumber cardNumber) {
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            cards.add(new Card(cardNumber, cardSymbol));
        }
    }
}
