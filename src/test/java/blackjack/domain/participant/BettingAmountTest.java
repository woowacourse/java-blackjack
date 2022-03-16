package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {
    @ParameterizedTest
    @ValueSource(longs = {1000L, 1000_000L})
    @DisplayName("생성 확인")
    public void create(long amount) {
        BettingAmount bettingAmount = new BettingAmount(amount);
        assertThat(bettingAmount).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(longs = {999L, 1000_001L})
    @DisplayName("생성 실패")
    public void creat2(long amount) {
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}