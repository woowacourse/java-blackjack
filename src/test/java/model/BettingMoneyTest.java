package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    void 베팅_머니의_단위는_100이다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingMoney(101));
    }

    @Test
    void 베팅_머니가_100원_단위면_정상생성된다() {
        // given
        BettingMoney bettingMoney = new BettingMoney(1000);

        // then
        assertThat(bettingMoney.bettingMoney()).isEqualTo(1000);
    }

    @Test
    void 베팅_머니는_100_미만일_수_없다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingMoney(99));
    }

    @Test
    void 베팅_머니는_10억을_초과할_수_없다() {
        long billion = 1000000001L;
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BettingMoney(billion));
    }
}
