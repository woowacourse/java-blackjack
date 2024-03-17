package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackMoneyTest {

    @Test
    @DisplayName("실패: 음수 액수 배팅")
    void construct_Exception_MinusBetting() {
        assertThatCode(() -> new BettingMoney(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("실패: 0 액수 배팅")
    void construct_Exception_ZeroBetting() {
        assertThatCode(() -> new BettingMoney(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("성공: 양수 액수 배팅")
    void construct_NoException_PlusBetting() {
        assertThatCode(() -> new BettingMoney(1))
                .doesNotThrowAnyException();
    }
}
