package domain.status;

import static domain.Fixtures.ACE_CLOVER;
import static domain.Fixtures.TEN_CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Cards;
import domain.status.end.BlackJack;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    @Test
    void profitWeight() {
        Cards cards = new Cards(List.of(ACE_CLOVER, TEN_CLOVER));
        Status blackJack = new BlackJack(cards);
        assertThat(blackJack.profitWeight()).isEqualTo(BigDecimal.valueOf(1.5));
    }
}
