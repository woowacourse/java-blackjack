package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("베팅 금액 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BettingAmountTest {

    @Test
    void 베팅금액이_최소금액_미만이면_예외가_발생한다() {
        int inputAmount = 9_999;

        assertThatThrownBy(() -> new BettingAmount(inputAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 최소 10,000원부터 입력 가능합니다.");
    }

    @Test
    void 베팅금액이_최소금액_이상이면_예외가_발생하지않는다() {
        int inputAmount = 10_000;

        assertThatCode(() -> new BettingAmount(inputAmount))
                .doesNotThrowAnyException();
    }
}
