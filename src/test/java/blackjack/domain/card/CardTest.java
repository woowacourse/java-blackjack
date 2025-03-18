package blackjack.domain.card;

import static blackjack.fixture.CardFixture.DIAMOND_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void 같은_카드를_비교할_수_있다() {
        //given
        Card card1 = DIAMOND_EIGHT;
        Card card2 = DIAMOND_EIGHT;

        //when & then
        assertThat(card1).isEqualTo(card2);
    }
}
