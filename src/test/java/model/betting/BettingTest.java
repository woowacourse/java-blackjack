package model;

import static model.betting.Betting.BETTING_AMOUNT_LOWER_BOUND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.betting.Betting;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    void negativeBettingAmount() {
        assertThatThrownBy(() -> new Betting(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_AMOUNT_LOWER_BOUND_MESSAGE);
    }
}
