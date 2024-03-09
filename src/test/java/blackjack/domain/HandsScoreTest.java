package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandsScoreTest {

    @DisplayName("카드 점수를 더할 수 있다")
    @Test
    void should_addScore() {
        HandsScore handsScore = new HandsScore();

        int cardScore = 10;
        handsScore.addScore(cardScore);
        handsScore.addScore(cardScore);

        assertThat(handsScore.getScore()).isEqualTo(20);
    }

    @DisplayName("카드패의 점수가 21을 초과했는지 판단한다")
    @Test
    void should_judgeBurst() {
        HandsScore handsScore = new HandsScore();

        int cardScore = 21;
        handsScore.addScore(cardScore);
        assertFalse(handsScore.isBurst());

        handsScore.addScore(1);
        assertTrue(handsScore.isBurst());
    }
}
