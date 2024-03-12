package model.card;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RandomCard {

    private static final int END_OFFSET = 1;
    private static final List<CardNumber> cardNumbers = initCardNumbers();
    private static final List<CardShape> cardShapes = initCardShapes();

    private RandomCard() {
    }

    private static List<CardNumber> initCardNumbers() {
        return Arrays.stream(CardNumber.values())
            .toList();
    }

    private static List<CardShape> initCardShapes() {
        return Arrays.stream(CardShape.values())
            .toList();
    }

    public static List<Card> pickCards(int count) {
        return Stream.generate(RandomCard::pickCard)
            .limit(count)
            .toList();
    }

    public static Card pickCard() {
        CardNumber cardNumber = pickCardNumber();
        CardShape cardShape = pickCardShape();
        return new Card(cardNumber, cardShape);
    }

    private static CardNumber pickCardNumber() {
        int index = pickRandomIndex(cardNumbers.size());
        return cardNumbers.get(index);
    }

    private static CardShape pickCardShape() {
        int index = pickRandomIndex(cardShapes.size());
        return cardShapes.get(index);
    }

    private static int pickRandomIndex(int endExclusive) {
        return Randoms.pickNumberInRange(0, endExclusive - END_OFFSET);
    }
}
