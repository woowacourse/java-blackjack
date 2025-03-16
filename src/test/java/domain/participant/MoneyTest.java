package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @ParameterizedTest
    @CsvSource({
            "0", "-1000"
    })
    void 입력이_0보다_작거나_같으면_예외를_발생시킨다(int value) {
        // when & then
        assertThatThrownBy(() -> Money.createBettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 0보다 커야 합니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "10000", "99999", "100001", "100010"
    })
    void 입력이_10만_단위가_아니면_예외를_발생시킨다(int value) {
        // when & then
        assertThatThrownBy(() -> Money.createBettingMoney(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 10만원 단위입니다.");
    }

    @Test
    void 돈을_더할_수_있다() {
        // given
        Money money1 = Money.of(100000);
        Money money2 = Money.of(100000);
        // when
        Money actual = money1.plus(money2);
        // then
        assertThat(actual).isEqualTo(Money.of(200000));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 100000", "1.5, 150000", "0, 0", "-1, -100000"
    })
    void 돈을_곱할_수_있다(double count, int newValue) {
        // given
        Money money = Money.of(100000);
        Money expected = Money.of(newValue);
        // when
        Money actual = money.times(count);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
