package domain.status;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class BustTest {
    @Test
    void profitWeight() {
        Bust bust = new Bust();
        assertThat(bust.profitWeight()).isEqualTo(BigDecimal.valueOf(0));
    }
}
