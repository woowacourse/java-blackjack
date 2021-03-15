package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProfitTest {
    @ParameterizedTest
    @DisplayName("null값이 생성자에 들어오면, 예외 처리 한다.")
    @NullSource
    void nullThrowException(Double profit) {
        assertThatThrownBy(() -> new Profit(profit)).isInstanceOf(NullPointerException.class)
                .hasMessage("Null은 허용하지 않습니다.");
    }
}
