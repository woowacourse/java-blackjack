package domain.status;

import static org.assertj.core.api.Assertions.assertThat;

import domain.status.end.BlackJack;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    @Test
    void profitWeight() {
        BlackJack blackJack = new BlackJack();
        assertThat(blackJack.profitWeight()).isEqualTo(BigDecimal.valueOf(1.5));
    }
}
