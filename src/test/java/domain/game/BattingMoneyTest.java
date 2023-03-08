package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("BattingMoney 는")
class BattingMoneyTest {

    @Test
    void 금액을_가지고_생성된다() {
        // given
        final int amount = 1000;

        // when
        BattingMoney battingMoney = BattingMoney.of(amount);

        // then
        assertThat(battingMoney.amount()).isEqualTo(amount);
    }

    @Test
    void 같은_금액인_경우_동등하다() {
        // given
        final int amount = 1000;
        BattingMoney battingMoney1 = BattingMoney.of(amount);
        BattingMoney battingMoney2 = BattingMoney.of(amount);

        // when & then
        assertThat(battingMoney1).isEqualTo(battingMoney2);
    }

    @ParameterizedTest(name = "돈을 더할 수 있다. {0}원 + {1}원은 {2}원이다")
    @CsvSource({
            "1000,2000,3000",
            "3000,2000,5000",
            "0,10000,10000"
    })
    void 돈을_더할_수_있다(final int beforeAmount, final int addedAmount, final int afterAmount) {
        // given
        BattingMoney before = BattingMoney.of(beforeAmount);
        BattingMoney added = BattingMoney.of(addedAmount);
        BattingMoney after = BattingMoney.of(afterAmount);

        // when
        BattingMoney result = before.plus(added);

        // then
        assertThat(result).isEqualTo(after);
    }

    @ParameterizedTest(name = "돈을 뺄 수 있다. {0}원 - {1}원은 {2}원이다")
    @CsvSource({
            "2000,1000,1000",
            "3000,2000,1000",
            "10000,0,10000"
    })
    void 돈을_뺄_수_있다(final int beforeAmount, final int minusAmount, final int afterAmount) {
        // given
        BattingMoney before = BattingMoney.of(beforeAmount);
        BattingMoney minus = BattingMoney.of(minusAmount);
        BattingMoney after = BattingMoney.of(afterAmount);

        // when
        BattingMoney result = before.minus(minus);

        // then
        assertThat(result).isEqualTo(after);
    }

    @ParameterizedTest(name = "돈을 n배 할 수 있다. {0}원을 {1} 배 하면 {2}원이다")
    @CsvSource({
            "2000,1.5,3000",
            "1000,1,1000",
            "3000,2,6000"
    })
    void 돈을_곱할_수_있다(final int beforeAmount, final double times, final int afterAmount) {
        // given
        BattingMoney before = BattingMoney.of(beforeAmount);
        BattingMoney after = BattingMoney.of(afterAmount);

        // when
        BattingMoney result = before.times(times);

        // then
        assertThat(result).isEqualTo(after);
    }
}