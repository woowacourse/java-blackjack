package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("베팅 금액 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {10000, 30000})
    void 베팅_금액_생성_및_비교(int money) {
        BettingMoney bettingMoney = new BettingMoney(money);

        assertThat(bettingMoney.getBettingMoney()).isEqualTo(money);
    }

    @DisplayName("베팅 금액 생성 에러 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -10000})
    void 베팅_금액_에러_테스트(int money) {
        assertThatThrownBy(() -> {
            new BettingMoney(money);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 0보다 커야합니다.");
    }

}