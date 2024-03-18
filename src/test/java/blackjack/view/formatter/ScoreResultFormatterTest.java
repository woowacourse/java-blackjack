package blackjack.view.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreResultFormatterTest {
    @Test
    @DisplayName("플레이어의 이름을 형식에 맞게 포맷한다.")
    void format() {
        assertThat(ScoreResultFormatter.format(20)).isEqualTo(" - 결과: 20");
    }
}
