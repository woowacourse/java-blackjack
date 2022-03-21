package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.BetMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {

    @DisplayName("배팅 금액을 받아 겜블러를 정상적으로 생성하는지 확인한다.")
    @Test
    void create_valid() {
        assertDoesNotThrow( () -> new Gambler("돌범", new BetMoney(1000)));
    }

    @DisplayName("잘못된 범위의 배팅 금액을 받아 겜블러를 생성시 예외를 발생시키는지 확인한다.")
    @Test
    void create_invalid() {
        assertThatThrownBy(() -> new Gambler("돌범", new BetMoney(-1000)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("범위");
    }
}
