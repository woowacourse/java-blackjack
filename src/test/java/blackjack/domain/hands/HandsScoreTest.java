package blackjack.domain.hands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HandsScoreTest {

    @DisplayName("버스트가 아니라면 에이스를 업그레이드 할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {11, 10, 0})
    void should_upgradeAce_When_NotBust(int handsScore) {
        assertThat(HandsScore.upgradeAceWhenNotBust(handsScore))
                .isEqualTo(new HandsScore(handsScore + 10));
    }

    @DisplayName("높은 점수를 판단할 수 있다")
    @Test
    void isHigherThan() {
        HandsScore highScore = new HandsScore(10);
        HandsScore lowScore = new HandsScore(0);

        assertAll(
                () -> assertTrue(highScore.isHigherThan(lowScore)),
                () -> assertFalse(lowScore.isHigherThan(highScore))
        );
    }

    @DisplayName("같은 점수를 판단할 수 있다")
    @Test
    void isSame() {
        HandsScore otherScore = new HandsScore(10);
        HandsScore sameScore1 = new HandsScore(5);
        HandsScore sameScore2 = new HandsScore(5);

        assertAll(
                () -> assertTrue(sameScore1.isSame(sameScore2)),
                () -> assertFalse(sameScore1.isSame(otherScore))
        );
    }
    @DisplayName("낮은 점수를 판단할 수 있다")
    @Test
    void isLowerThan() {
        HandsScore highScore = new HandsScore(10);
        HandsScore lowScore = new HandsScore(0);

        assertAll(
                () -> assertTrue(lowScore.isLowerThan(highScore)),
                () -> assertFalse(highScore.isLowerThan(lowScore))
        );
    }
}
