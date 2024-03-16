package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("스코어가 0보다 작을 경우 예외를 발생시킨다.")
    @Test
    void createBetWalletByLowAmount() {
        //given
        int score = -1;

        //when, then
        assertThatThrownBy(() -> new Score(score))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
