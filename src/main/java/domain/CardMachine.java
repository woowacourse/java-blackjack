package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardMachine {

    private final List<Card> cards = new ArrayList<>();

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
            cards.add(new Card(cardNumber, cardShape));
        }
    }
}
