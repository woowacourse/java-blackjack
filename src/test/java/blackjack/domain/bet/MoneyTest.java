package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("돈 테스트")
class MoneyTest {

    @DisplayName("생성 시 범위를 지키지 못하면 생성 검증에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {-1_000_000_001, 1_000_000_001})
    void testCreateMoneyWithInvalidRange(int amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 -1000000000부터 1000000000이하까지 가능합니다.");
    }


    @DisplayName("생성 검증을 통과하면 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 2000, 30000})
    void testCreateMoneyWithValidData(int amount) {
        assertThatCode(() -> new Money(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("돈에 특정 배율을 적용한 결과를 알 수 있다")
    @Test
    void testMultiply() {
        Money money = new Money(1000);
        assertThat(money.multiply(1.5).getAmount()).isEqualTo(1500);
    }

    @DisplayName("돈에 돈을 더할 수 있다")
    @Test
    void testAddMoney() {
        Money money = new Money(1000);
        Money added = money.add(new Money(1000));
        assertThat(added.getAmount()).isEqualTo(2000);
    }

    @DisplayName("부호를 반전한 돈을 계산할 수 있다")
    @Test
    void testInverseMoney() {
        Money money = new Money(1000);
        assertThat(money.inverse().getAmount()).isEqualTo(-1000);
    }

    @DisplayName("돈에 특정 이율을 적용한 Profit을 계산할 수 있다")
    @Test
    void testCalculateProfit() {
        Money money = new Money(1000);
        assertThat(money.calculateProfit(1.5).getValue()).isEqualTo(1500);
    }
}
