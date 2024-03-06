package fixture;

import blackjack.domain.Card;
import blackjack.domain.CardNumber;

import java.util.Arrays;

import static blackjack.domain.CardShape.HEART;

public class CardFixture {

    public static Card from(int number) {
        CardNumber cardNumber = Arrays.stream(CardNumber.values())
                .filter(cn -> cn.getValue() == number)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return new Card(HEART, cardNumber);
    }
}
