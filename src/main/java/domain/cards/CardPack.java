package domain.cards;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPack {

    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    public CardPack() {
        this.cards = new ArrayList<>();
        generateRandomCards();
    }

    private void generateRandomCards() {
        generateByNumberAndShape();
        Collections.shuffle(cards);
    }

    private void generateByNumberAndShape() {
        for (CardNumber cardNumber : CardNumber.values()) {
            generateByShape(cardNumber);
        }
    }

    private void generateByShape(CardNumber cardNumber) {
        for (CardShape cardShape : CardShape.values()) {
            cards.add(new Card(cardNumber, cardShape));
        }
    }

    public Card pickOneCard() {
        return cards.remove(FIRST_CARD);
    }

    public boolean isUsedAll() {
        return cards.isEmpty();
    }
}
