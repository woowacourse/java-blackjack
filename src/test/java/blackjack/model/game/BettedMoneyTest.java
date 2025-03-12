package blackjack.model.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettedMoneyTest {

    @Test
    void 베팅_금액을_가지고_객체를_생성한다() {
        // Given
        int money = 1_000_000_000;

        // When & Then
        assertThatCode(() -> new BettedMoney(money))
                .doesNotThrowAnyException();
    }
    
    @Test
    void 베팅은_0원_이상_가능하다() {
        // Given
        int money = -1;
        
        // When & Then
        assertThatThrownBy(() -> new BettedMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0원 이상 베팅 가능합니다.");
    }

    @Test
    void 베팅은_최대_10억까지만_가능하다() {
        // Given
        int money = 1_000_000_001;

        // When & Then
        assertThatThrownBy(() -> new BettedMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 10억 원까지 베팅 가능합니다.");
    }
}
