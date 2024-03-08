package model.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CardDispenser {

    private static final int END_OFFSET = 1;
    private static CardDispenser cardDispenser = new CardDispenser();

    private final Map<Integer, CardNumber> cardNumbers;
    private final Map<Integer, CardShape> cardShapes;

    private CardDispenser() {
        cardNumbers = initialCardNumbers();
        cardShapes = initialCardShapes();
    }

    private Map<Integer, CardNumber> initialCardNumbers() {
        Map<Integer, CardNumber> numbers = new HashMap<>();
        int index = 0;
        for (CardNumber cardNumber : CardNumber.values()) {
            numbers.put(index++, cardNumber);
        }
        return numbers;
    }

    private Map<Integer, CardShape> initialCardShapes() {
        Map<Integer, CardShape> shapes = new HashMap<>();
        int index = 0;
        for (CardShape cardShape : CardShape.values()) {
            shapes.put(index++, cardShape);
        }
        return shapes;
    }

    public static CardDispenser getCardDispenser() {
        if (cardDispenser == null) {
            cardDispenser = new CardDispenser();
        }
        return cardDispenser;
    }

    public Cards dispenseCards(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> dispenseCard())
            .collect(collectingAndThen(toList(), Cards::new));
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
