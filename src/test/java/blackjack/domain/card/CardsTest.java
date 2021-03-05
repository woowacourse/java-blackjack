package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class CardsTest {
    @Test
    void create() {
        assertThatCode(() -> {
            new Cards(Arrays.asList(Card.create(Suit.CLUB, Denomination.KING)));
        }).doesNotThrowAnyException();
    }
}
