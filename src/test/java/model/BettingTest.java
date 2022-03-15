package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.betting.Betting;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    void negativeBettingAmount() {
        assertThatThrownBy(() -> new Betting(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액을 음수로 넣으시면 안됩니다.");
    }
}
