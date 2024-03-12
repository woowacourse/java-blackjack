package blackjack.domain.card;

import static blackjack.domain.card.CardShape.HEART;

import java.util.Arrays;

public class TestCardCreator {

    private TestCardCreator() {
    }

    public static Card from(int number) {
        CardNumber cardNumber = Arrays.stream(CardNumber.values())
                .filter(cn -> cn.getValue() == number)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return new Card(HEART, cardNumber);
    }
}
