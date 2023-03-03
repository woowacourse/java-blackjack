package domain;

import java.util.ArrayList;
import java.util.List;

public class CardBox {
    private static final String[] CARD_NUMBER = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final int[] CARD_VALUE = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private static final String[] CARD_SHAPE = {"스페이드", "하트", "클로버", "다이아몬드"};

    public List<Card> cardBox = new ArrayList<>();

    public CardBox() {
        for (String shape : CARD_SHAPE) {
            initCards(shape);
        }
    }

    private void initCards(final String cardShape) {
        for (int i = 0; i < CARD_NUMBER.length; i++) {
            cardBox.add(new Card(CARD_NUMBER[i] + cardShape, CARD_VALUE[i]));
        }
    }

    public Card get(final int index) {
        Card card = cardBox.get(index);
        cardBox.remove(card);
        return card;
    }
}
