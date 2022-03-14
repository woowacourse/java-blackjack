package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetTest {

    @Test
    @DisplayName("입력 금액이 0원 이하일 경우 예외를 발생시킨다.")
    void createGamerExceptionMoneyEqualStandard() {
        assertThatThrownBy(() -> new Bet(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅은 1원부터 가능합니다.");
    }
}
