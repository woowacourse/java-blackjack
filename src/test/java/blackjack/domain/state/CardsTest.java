package blackjack.domain.state;

import blackjack.domain.Fixture;
import blackjack.state.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {

    @DisplayName("Bust인지 아닌지 판별")
    @Test
    void isBust() {
        Cards cards = new Cards(Arrays.asList(Fixture.CLUBS_TEN,Fixture.CLUBS_ACE,Fixture.CLUBS_KING));

        assertTrue(cards.isBust());
    }
}
