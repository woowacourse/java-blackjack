package model.card;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.Map;

public class CardDispenser {

    private static final int END_OFFSET = 1;
    private static CardDispenser cardDispenser;

    private final Map<Integer, CardNumber> cardNumbers;
    private final Map<Integer, CardShape> cardShapes;

    private CardDispenser() {
        cardNumbers = initialCardNumbers();
        cardShapes = initialCardShapes();
    }

    private Map<Integer, CardNumber> initialCardNumbers() {
        Map<Integer, CardNumber> cardNumbers = new HashMap<>();
        int index = 0;
        for (CardNumber cardNumber : CardNumber.values()) {
            cardNumbers.put(index++, cardNumber);
        }
        return cardNumbers;
    }

    private Map<Integer, CardShape> initialCardShapes() {
        Map<Integer, CardShape> cardShapes = new HashMap<>();
        int index = 0;
        for (CardShape cardShape : CardShape.values()) {
            cardShapes.put(index++, cardShape);
        }
        return cardShapes;
    }

    public static CardDispenser getCardDispenser() {
        if (cardDispenser == null) {
            cardDispenser = new CardDispenser();
        }
        return cardDispenser;
    }

    public Card dispenseCard() {
        CardNumber cardNumber = cardNumbers.get(pickRandomNumber(cardNumbers.size()));
        CardShape cardShape = cardShapes.get(pickRandomNumber(cardShapes.size()));
        return new Card(cardNumber, cardShape);
    }

    private int pickRandomNumber(int endExclusive) {
        return Randoms.pickNumberInRange(0, endExclusive - END_OFFSET);
    }
}
