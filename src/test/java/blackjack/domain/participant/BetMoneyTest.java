package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetMoneyTest {

    @DisplayName("배팅금액이 음수이면 객체는 생성되지 않는다")
    @Test
    void should_throwIllegalArgumentException_When_NotNumber() {
        assertThatThrownBy(() -> new BetMoney(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
