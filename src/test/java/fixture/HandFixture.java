package fixture;

import blackjack.domain.Hand;

import java.util.Arrays;

public class HandFixture {

    public static Hand of(int... numbers) {
        return new Hand(Arrays.stream(numbers)
                .mapToObj(CardFixture::from)
                .toList());
    }
}
