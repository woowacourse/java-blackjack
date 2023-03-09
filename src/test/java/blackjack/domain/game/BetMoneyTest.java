package blackjack.domain.game;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class BetMoneyTest {


    @ParameterizedTest(name = "배팅 금액을 생성한다. 입력 값: {0}")
    @ValueSource(ints = {1000, 100000000})
    void 베팅_금액을_생성한다(final int money) {
        final BetMoney betMoney = BetMoney.createBetMoney(money);

        final int value = betMoney.getAmount();

        assertThat(value).isEqualTo(money);
    }

    @ParameterizedTest(name = "금액이 1,000 미만 100,000,000 초과이면 예외를 던진다. 입력 값: {0}")
    @ValueSource(ints = {999, 100000001})
    void 금액이_1000_미만_100000000_초과이면_예외를_던진다(final int money) {
        assertThatThrownBy(() -> BetMoney.createBetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BetMoney.INVALID_ACCOUNT_RANGE_MESSAGE + money);
    }

    @ParameterizedTest
    @CsvSource({"true, 2500", "false, 2000"})
    void 이긴_배팅_금액을_얻는다(final boolean isBlackjack, final int expectedMoney) {
        final BetMoney betMoney = BetMoney.createBetMoney(1000);

        final BetMoney winMoney = betMoney.winMoney(isBlackjack);

        assertThat(winMoney.getAmount()).isEqualTo(expectedMoney);
    }

    @Test
    void 진_배팅_금액을_얻는다() {
        final BetMoney betMoney = BetMoney.createBetMoney(1000);

        final BetMoney loseMoney = betMoney.loseMoney();

        assertThat(loseMoney.getAmount()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({"10000, 5000, 5000", "5000, 10000, -5000"})
    void 뺄셈_결과를_반환한다(final int original, final int afterGame, final int expectedDifference) {
        final BetMoney originalMoney = BetMoney.createBetMoney(original);
        final BetMoney afterGameMoney = BetMoney.createBetMoney(afterGame);

        final BetMoney income = afterGameMoney.subtract(originalMoney);

        assertThat(income).isEqualTo(BetMoney.createBetMoney(expectedDifference));
    }
}
