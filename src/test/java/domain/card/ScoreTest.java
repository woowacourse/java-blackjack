package domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScoreTest {

    private Score score;

    @BeforeEach
    void init() {
        score = Score.create(10);
    }

    @Test
    @DisplayName("create()는 호출하면, Score를 생성한다")
    void create_whenCall_thenSuccess() {
        final Score score = assertDoesNotThrow(() -> Score.create(10));
        assertThat(score)
                .isExactlyInstanceOf(Score.class);
    }
}
