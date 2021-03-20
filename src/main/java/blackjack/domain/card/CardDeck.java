package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {

    private static final String EMPTY_CARD_ERROR = "[ERROR] 카드를 모두 소진했습니다.";

    private final Stack<Card> deck = new Stack<>();

    public CardDeck() {
        generateCardWithSymbol();
        Collections.shuffle(this.deck);
    }

    private void generateCardWithSymbol() {
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            generateCardWithNumber(cardSymbol);
        }
    }

    private void generateCardWithNumber(CardSymbol cardSymbol) {
        for (CardNumber cardNumber : CardNumber.values()) {
            deck.add(new Card(cardNumber, cardSymbol));
        }
    }

    public synchronized UserDeck generateUserDeck() {
        UserDeck userDeck = new UserDeck();
        userDeck.draw(this.draw());
        userDeck.draw(this.draw());
        return userDeck;
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CARD_ERROR);
        }
        return deck.pop();
    }
}
