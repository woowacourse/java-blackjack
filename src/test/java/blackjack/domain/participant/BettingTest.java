package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingTest {
    @ParameterizedTest
    @DisplayName("null값이 생성자에 들어오면, 예외 처리 한다.")
    @NullSource
    void nullThrowException(Double betting) {
        assertThatThrownBy(() -> new Betting(betting)).isInstanceOf(NullPointerException.class)
                .hasMessage("Null은 허용하지 않습니다.");
    }

    @Test
    @DisplayName("null값이 생성자에 들어오면, 예외 처리 한다.")
    void minValueThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new Betting((double) -2)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 0원보다 많아야 합니다.");
    }
}
