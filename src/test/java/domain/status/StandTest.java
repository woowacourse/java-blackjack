package domain.status;

import static domain.Fixtures.JACK_SPADE;
import static domain.Fixtures.QUEEN_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.status.end.Stand;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class StandTest {
    @Test
    void profitWeight() {
        Cards cards = new Cards(List.of(QUEEN_DIAMOND, JACK_SPADE));
        Status stand = new Stand(cards);
        assertThat(stand.profitWeight()).isEqualTo(BigDecimal.valueOf(1));
    }
}
