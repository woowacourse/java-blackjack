package blackjack.domain.machine;

import static blackjack.domain.card.Fixtures.SPADE_ACE;
import static blackjack.domain.card.Fixtures.SPADE_EIGHT;
import static blackjack.domain.card.Fixtures.SPADE_FOUR;
import static blackjack.domain.card.Fixtures.SPADE_JACK;
import static blackjack.domain.card.Fixtures.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("스코어 클래스 생성 확인")
    void createScore() {
        Score score = new Score(Set.of(SPADE_ACE, SPADE_JACK));

        assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    @DisplayName("card 점수 합산 확인")
    public void checkSumPoints() {
        Score score = new Score(Set.of(SPADE_EIGHT, SPADE_TWO));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(10);
    }

    @Test
    @DisplayName("ace 가 있을 때 21점 넘어가면 1점으로 계산 확인")
    public void checkAcePointWillBeOne() {
        Score score = new Score(Set.of(SPADE_ACE, SPADE_JACK, SPADE_FOUR));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(15);
    }

    @Test
    @DisplayName("ace 가 있을 때 21점 넘어가지 않으면 11점으로 계산 확인")
    public void checkAcePointWillBeEleven() {
        Score score = new Score(Set.of(SPADE_ACE, SPADE_JACK, SPADE_FOUR));
        int sumPoint = score.value();
        assertThat(sumPoint).isEqualTo(15);
    }

    @Test
    @DisplayName("blackjack 카드 확인")
    public void checkBlackjackCards() {
        Score score = new Score(Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.SPADE, Rank.JACK)));
        assertThat(score.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("3개 카드의 합이 21일 때 블랙잭이 아님을 확인")
    public void checkNoBlackjackWithThreeCards() {
        Score score = new Score(Set.of(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.SPADE, Rank.FIVE),
                new Card(Suit.SPADE, Rank.SIX)));
        assertThat(score.isBlackjack()).isFalse();
    }
}
