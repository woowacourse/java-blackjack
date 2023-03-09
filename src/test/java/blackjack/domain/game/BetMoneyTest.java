package blackjack.domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BetMoneyTest {


    @ParameterizedTest(name = "배팅 금액을 생성한다. 입력 값: {0}")
    @ValueSource(ints = {1000, 100000000})
    void 베팅_금액을_생성한다(final int money) {
        final BetMoney betMoney = new BetMoney(money);

        final int value = betMoney.getAmount();

        assertThat(value).isEqualTo(money);
    }

    @ParameterizedTest(name = "금액이 1,000 미만 100,000,000 초과이면 예외를 던진다. 입력 값: {0}")
    @ValueSource(ints = {999, 100000001})
    void 금액이_1000_미만_100000000_초과이면_예외를_던진다(final int money) {
        assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BetMoney.INVALID_ACCOUNT_RANGE_MESSAGE + money);
    }
}
