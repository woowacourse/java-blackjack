package blackjack.domain.card;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public final class DeckFactory {

    public static Deck createDeck(DeckType deckType) {
        if (deckType == DeckType.BLACKJACK) {
            return createBlackJackDeck();
        }
        return null;
    }

    private static Deck createBlackJackDeck() {
        Stack<Card> cards = Arrays.stream(CardNumber.values())
                .flatMap(cardNumber -> Arrays.stream(CardSymbol.values())
                        .map(cardSymbol -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toCollection(Stack::new));

        return new Deck(cards);
    }

}
