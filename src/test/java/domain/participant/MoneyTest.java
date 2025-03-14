package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MoneyTest {

    @Test
    void 현재_금액의_비율을_곱하여_반환한다() {
        // given
        Money money = new Money(10_000);
        double rate = 0.3;

        // when
        Money result = money.multiply(rate);

        // then
        assertThat(result).isEqualTo(new Money(3_000));
    }

    @Test
    void 금액이_음수인_경우_예외가_발생한다() {
        // given
        int amount = -1_000;

        // when & then
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }

}
