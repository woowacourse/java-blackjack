package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.Fixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("Fixture 상수 테스트")
    void of() {
        final Card card = Card.of(Denomination.ACE, Symbol.SPADE);

        assertThat(card).isSameAs(Fixture.SPADE_ACE);
    }
}
