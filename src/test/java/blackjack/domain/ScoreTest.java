package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    @DisplayName("점수 계산")
    void calculateScore(){
        Score score = new Score();
        score.calculateScore(List.of(TrumpNumber.FIVE, TrumpNumber.NINE));

        Assertions.assertThat(score.getScore()).isEqualTo(14);
    }

}
