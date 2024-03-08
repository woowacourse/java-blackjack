package fixture;

import blackjack.domain.card.CardDeck;

import java.util.Arrays;

public class CardDeckFixture {

    public static CardDeck of(int... numbers) {
        return new CardDeck(Arrays.stream(numbers)
                .mapToObj(CardFixture::from)
                .toList());
    }
}
