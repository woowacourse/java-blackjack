package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BettingMoneyTest {

    @Test
    @DisplayName("create()는 호출하면 BettingMoney를 생성한다.")
    void create_whenCall_thenSuccess() {
        BettingMoney bettingMoney = assertDoesNotThrow(() -> BettingMoney.create(1000));
        assertThat(bettingMoney)
                .isInstanceOf(BettingMoney.class);
    }
}
