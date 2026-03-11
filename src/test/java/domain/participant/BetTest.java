package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exception.OutOfRangeException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetTest {
    @DisplayName("기본 생성 테스트")
    @Test
    void 기본_생성() {
        Bet bet = new Bet(2000);
        assertThat(bet.amount()).isEqualTo(2000);
    }

    @DisplayName("베팅 금액 0원시 예외")
    @Test
    void 베팅금_0_불가(){
        assertThatThrownBy(() -> new Bet(0))
                .isInstanceOf(OutOfRangeException.class)
                .hasMessageContaining("베팅 금액은 최소 1원은 넘어야 합니다.");
    }

    @DisplayName("베팅 금액 음수일시 예외")
    @Test
    void 베팅금_음수_불가(){
        assertThatThrownBy(() -> new Bet(-1))
                .isInstanceOf(OutOfRangeException.class);
    }
}