package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private static final int MINIMUM_CARDBOX_INDEX = 0;
    private static final int MAXIMIM_CARDBOX_INDEX = 51;
    private static final String[] CARD_NUMBER = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static final int[] CARD_VALUE = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private static final String[] CARD_SHAPE = {"스페이드", "하트", "클로버", "다이아몬드"};

    private static List<Card> cardBox = new ArrayList<>();

    static {
        for (String cardShape : CARD_SHAPE) {
            initCards(cardShape);
        }
    }

    private static void initCards(final String cardShape) {
        for (int i = 0; i < CARD_NUMBER.length; i++) {
            cardBox.add(new Card(CARD_NUMBER[i] + cardShape, CARD_VALUE[i]));

        }
    }

    public static Card get(final int index) {
        if (MINIMUM_CARDBOX_INDEX > index || MAXIMIM_CARDBOX_INDEX < index) {
            throw new IllegalArgumentException("잘못된 카드 위치를 참조했습니다");
        }
        return cardBox.get(index).copyCard();
    }
}
