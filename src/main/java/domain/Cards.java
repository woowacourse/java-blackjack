package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

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

    public Card giveCard() {
        return cards.remove(0);
    }

    public void add(final List<Card> initCards) {
        cards.addAll(initCards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateTotal() {
        List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toList());
        return CardNumber.getTotal(cardNumbers);
    }
}
