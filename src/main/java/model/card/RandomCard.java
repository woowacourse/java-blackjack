package model.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class RandomCard {

    private static final int END_OFFSET = 1;
    private static RandomCard randomCardPicker = new RandomCard();

    private final Map<Integer, CardNumber> cardNumbers;
    private final Map<Integer, CardShape> cardShapes;

    private RandomCard() {
        cardNumbers = initCardNumbers();
        cardShapes = initCardShapes();
    }

    private Map<Integer, CardNumber> initCardNumbers() {
        return Arrays.stream(CardNumber.values())
            .collect(toMap(Enum::ordinal, Function.identity()));
    }

    private Map<Integer, CardShape> initCardShapes() {
        return Arrays.stream(CardShape.values())
            .collect(toMap(Enum::ordinal, Function.identity()));
    }

    public static RandomCard getRandomCard() {
        if (randomCardPicker == null) {
            randomCardPicker = new RandomCard();
        }
        return randomCardPicker;
    }

    public Cards pickCards(int count) {
        return Stream.generate(this::pickCard)
            .limit(count)
            .collect(collectingAndThen(toList(), Cards::new));
    }

    public Card pickCard() {
        int numberKey = pickRandomKey(cardNumbers.size());
        int shapeKey = pickRandomKey(cardShapes.size());
        CardNumber cardNumber = cardNumbers.get(numberKey);
        CardShape cardShape = cardShapes.get(shapeKey);
        return new Card(cardNumber, cardShape);
    }

    private int pickRandomKey(int endExclusive) {
        return Randoms.pickNumberInRange(0, endExclusive - END_OFFSET);
    }
}
