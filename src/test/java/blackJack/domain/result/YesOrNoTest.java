package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class YesOrNoTest {

    @ParameterizedTest(name = "y 또는 n 입력을 통한 참가자가 카드를 더 뽑을 수 있는지 확인할 수 있다.")
    @CsvSource({"y, YES", "n, NO"})
    void hitRequestYesOrNo(String value, YesOrNo yesOrNo) {
        assertThat(YesOrNo.find(value)).isEqualTo(yesOrNo);
    }

    @ParameterizedTest(name = "참가자가 카드를 더 뽑는 입력에서 y 또는 n 입력이 이루어지지 않은 경우 예외가 발생한다.")
    @ValueSource(strings = {" ", "", "not", "1"})
    void hitRequestNotYesOrNo(String value) {
        assertThatThrownBy(() -> YesOrNo.find(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("y 또는 n을 입력해주세요.");
    }
}