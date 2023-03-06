package blackjack.domain.participant;

import static blackjack.domain.participant.Result.DRAW;
import static blackjack.domain.participant.Result.LOSE;
import static blackjack.domain.participant.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Nested
    class reverse_메서드는 {

        @Test
        void WIN_이라면_LOSE_반환한다() {
            final Result result = WIN;

            assertThat(result.reverse()).isEqualTo(LOSE);
        }

        @Test
        void LOSE_라면_WIN_반환한다() {
            final Result result = LOSE;

            assertThat(result.reverse()).isEqualTo(WIN);
        }

        @Test
        void DRAW_라면_DRAW_반환한다() {
            final Result result = DRAW;

            assertThat(result.reverse()).isEqualTo(DRAW);
        }
    }
}
