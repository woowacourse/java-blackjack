package blackjack.fixture;

import java.util.Arrays;
import java.util.Stack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.CardStrategy;
import blackjack.domain.deck.Deck;

public class DeckFixture {

    public static Deck deckOf(CardNumber... cardNumbers) {
        Stack<Card> stack = new Stack<>();
        Arrays.stream(cardNumbers).forEach(cardNumber -> stack.add(CardFixture.cardOf(cardNumber)));

        CardStrategy cardStrategy = new CardStrategy() {
            @Override
            public Stack<Card> generateDeck() {
                return stack;
            }
        };
        return Deck.generateFrom(cardStrategy);
    }
}
