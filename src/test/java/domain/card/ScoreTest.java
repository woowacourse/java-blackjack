package domain.card;

import domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    @Test
    @DisplayName("생성자 테스트")
    void Score() {
        assertThat(new Score(21)).isInstanceOf(Score.class);
        assertThatThrownBy(() -> new Score(-1))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("negative");
        assertThatThrownBy(() -> new Score(1,-1))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("negative");
    }
}
