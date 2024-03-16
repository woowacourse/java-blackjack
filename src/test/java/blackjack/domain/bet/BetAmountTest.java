package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("돈 테스트")
class BetAmountTest {

    @DisplayName("생성 시 범위를 지키지 못하면 생성 검증에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 998, 888, 1_000_000_001})
    void testCreateMoneyWithInvalidRange(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 1000부터 1000000000이하까지 가능합니다.");
    }


    @DisplayName("생성 검증을 통과하면 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 30000})
    void testCreateMoneyWithValidData(int amount) {
        assertThatCode(() -> new BetAmount(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("돈에 특정 이율을 적용한 Profit을 계산할 수 있다")
    @Test
    void testCalculateProfit() {
        BetAmount betAmount = new BetAmount(1000);
        assertThat(betAmount.calculateProfit(1.5).getValue()).isEqualTo(1500);
    }
}
