package blackjack.domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MoneyTest {


    @ParameterizedTest(name = "배팅 금액을 생성한다. 입력 값: {0}")
    @ValueSource(ints = {1000, 100000000})
    void 베팅_금액을_생성한다(final int money) {
        final Money bettingMoney = Money.createMoneyForBetting(money);

        final int value = bettingMoney.getAmount();

        assertThat(value).isEqualTo(money);
    }

    @ParameterizedTest(name = "금액이 1,000 미만 100,000,000 초과이면 예외를 던진다. 입력 값: {0}")
    @ValueSource(ints = {999, 100000001})
    void 금액이_1000_미만_100000000_초과이면_예외를_던진다(final int money) {
        assertThatThrownBy(() -> Money.createMoneyForBetting(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Money.INVALID_BETTING_AMOUNT_RANGE_MESSAGE + money);
    }

    @ParameterizedTest
    @CsvSource({"-1, -10000", "0, 0", "1, 10000", "1.5, 15000"})
    void 수익을_반환한다(final double payoutRatio, final int amount) {
        final Money moneyForBetting = Money.createMoneyForBetting(10000);

        final Money profit = moneyForBetting.multiply(payoutRatio);

        assertThat(profit.getAmount()).isEqualTo(amount);
    }
}
