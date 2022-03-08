package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Cards {

    private final Stack<Card> cards = new Stack<>();

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void generate() {
        for (CardNumber cardNumber : CardNumber.values()) {
            selectCardShape(cardNumber);
        }
        Collections.shuffle(cards);
    }

    private void selectCardShape(final CardNumber cardNumber) {
        for (CardShape cardShape : CardShape.values()) {
            cards.push(new Card(cardNumber, cardShape));
        }
    }

    public Card giveCard() {
        return cards.pop();
    }
}
