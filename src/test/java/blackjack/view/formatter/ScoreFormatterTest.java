package blackjack.view.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreFormatterTest {
    @Test
    @DisplayName("플레이어의 이름을 형식에 맞게 포맷한다.")
    void format() {
        assertThat(ScoreFormatter.format(20)).isEqualTo(" - 결과: 20");
    }
}
