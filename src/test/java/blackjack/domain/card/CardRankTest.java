package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardRankTest {

    @DisplayName("점수가 21을 초과하지 않으면 Ace는 11로 계산한다.")
    @Test
    void testAdjustAceScore() {
        // given
        Score score = new Score(11);

        // when
        Score actual = CardRank.adjustAceScore(score);

        // then
        assertThat(actual).isEqualTo(new Score(21));
    }

    @DisplayName("점수가 21을 초과하면 Ace를 11로 계산하지 않는다.")
    @Test
    void testNotAdjustAceScore() {
        // given
        Score score = new Score(12);

        // when
        Score actual = CardRank.adjustAceScore(score);

        // then
        assertThat(actual).isEqualTo(new Score(12));
    }

    @Test
    void allCardRanks() {
        // when
        List<CardRank> actual = CardRank.allCardRanks();

        // then
        assertThat(actual).containsExactlyInAnyOrder(
                CardRank.ACE,
                CardRank.TWO,
                CardRank.THREE,
                CardRank.FOUR,
                CardRank.FIVE,
                CardRank.SIX,
                CardRank.SEVEN,
                CardRank.EIGHT,
                CardRank.NINE,
                CardRank.TEN,
                CardRank.JACK,
                CardRank.QUEEN,
                CardRank.KING
        );
    }
}
