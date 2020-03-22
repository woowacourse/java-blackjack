package blackjack.domain.report;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameReportTest {

    @DisplayName("이름과 결과값이 null이면 exception")
    @Test
    void createValidation() {
        assertThatThrownBy(() -> new GameReport(null, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 비어있습니다.");
    }
}