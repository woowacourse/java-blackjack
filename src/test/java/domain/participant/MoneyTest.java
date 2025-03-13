package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "  "
    })
    void 입력이_공백이면_예외를_발생시킨다(String input) {
        // when & then
        assertThatThrownBy(() -> Money.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 공백이거나 null 입니다.");
    }

    @Test
    void 입력이_null이면_예외를_발생시킨다() {
        // when & then
        assertThatThrownBy(() -> Money.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 공백이거나 null 입니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "만", "10000원"
    })
    void 입력이_정수_형식이_아니면_예외를_발생시킨다(String rawValue) {
        // when & then
        assertThatThrownBy(() -> Money.of(rawValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 정수 형식이 아닙니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "0", "-1000"
    })
    void 입력이_0보다_작거나_같으면_예외를_발생시킨다(String rawValue) {
        // when & then
        assertThatThrownBy(() -> Money.of(rawValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력은 0보다 커야 합니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "10000", "99999", "100001", "100010"
    })
    void 입력이_10만_단위가_아니면_예외를_발생시킨다(String rawValue) {
        // when & then
        assertThatThrownBy(() -> Money.of(rawValue))
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
}