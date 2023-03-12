package domain.status;

import static domain.Fixtures.JACK_CLOVER;
import static domain.Fixtures.JACK_SPADE;
import static domain.Fixtures.TEN_CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.status.end.Bust;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class BustTest {
    @Test
    void profitWeight() {
        Cards cards = new Cards(List.of(TEN_CLOVER, JACK_SPADE, JACK_CLOVER));
        Status bust = new Bust(cards);
        assertThat(bust.profitWeight()).isEqualTo(BigDecimal.valueOf(1));
    }
}
