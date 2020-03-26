package domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfitTest {

    @Test
    void multiply() {
        Profit profit = new Profit(100);
        Profit multiply = profit.multiply(-1);
        assertThat(multiply).isEqualTo(new Profit(-100));
        assertThat(multiply.getValue()).isEqualTo(-100);
    }
}