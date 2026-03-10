package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public record Card(
        CardValue cardValue,
        CardShape cardShape
) {

    public static List<Card> makeCardsWith(CardShape cardShape) {
        return Arrays.stream(CardValue.values())
                .map(cardValue -> new Card(cardValue, cardShape))
                .toList();
    }

    public boolean isAce() {
        return cardValue.isAce();
    }

    public String getName() {
        return cardValue.getName() + cardShape.getName();
    }

    public int getCardValue() {
        return cardValue.getValue();
    }

}
