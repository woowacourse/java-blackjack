package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class YesOrNoTest {

    @ParameterizedTest()
    @CsvSource(value = {"y,YES", "n,NO"})
    void 입력값에_따라_YES_NO를_알맞게_반환한다(String input, YesOrNo expected) {
        assertThat(YesOrNo.find(input)).isEqualTo(expected);
    }

    @Test
    void 입력값이_y_n이_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> YesOrNo.find("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }
}