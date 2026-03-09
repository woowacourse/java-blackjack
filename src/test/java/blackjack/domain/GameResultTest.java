package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
    }

    @Test
    void 유저_승리_결과_저장_및_확인() {
        // given
        gameResult.add("흑곰", true);

        // when & then
        assertThat(gameResult.isUserWin("흑곰")).isTrue();
    }

    @Test
    void 유저_패배_결과_저장_및_확인() {
        // given
        gameResult.add("흑곰", false);

        // when & then
        assertThat(gameResult.isUserWin("흑곰")).isFalse();
    }

    @Test
    void 딜러_승리_횟수_계산() {
        // given
        gameResult.add("흑곰", false);
        gameResult.add("밀란", false);
        gameResult.add("로치", true);

        // when & then
        assertThat(gameResult.getDealerWinCount()).isEqualTo(2);
    }

    @Test
    void 유저_승리_횟수_계산() {
        // given
        gameResult.add("흑곰", true);
        gameResult.add("밀란", true);
        gameResult.add("로치", false);

        // when & then
        assertThat(gameResult.getUserWinCount()).isEqualTo(2);
    }

    @Test
    void 승리_횟수와_패배_횟수의_합은_총_유저_수() {
        // given
        gameResult.add("흑곰", true);
        gameResult.add("밀란", false);
        gameResult.add("로치", false);

        // when
        int total = gameResult.getDealerWinCount() + gameResult.getUserWinCount();

        // then
        assertThat(total).isEqualTo(3);
    }
}
