package blackjack.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.스페이드_10;
import static blackjack.domain.fixture.FixtureCard.클로버_10;
import static blackjack.domain.fixture.FixtureCard.하트_10;

class ScoreTest {

    @Test
    void 점수가_21점_이상이면_BUST() {
        // given
        Score score = new Score();

        // when
        score.calculateScore(new Hand(List.of(클로버_10, 스페이드_10, 하트_10)));

        // then
        Assertions.assertThat(score.isBust()).isTrue();
    }
}
