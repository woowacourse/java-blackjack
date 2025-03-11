package blackjack.domain.card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    void 카드_합이_21을_초과하지_않는_경우가_존재한다면_21보다_작은_수_중_최대값을_반환한다() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE)
        );
        Score score = new Score(new Cards(cards));

        //when
        int scoreResult = score.calculateMaxScore();

        //then
        Assertions.assertThat(scoreResult).isEqualTo(21);
    }

    @Test
    void 카드_합이_21을_초과하는_경우만_있다면_21과_가장_가까운_수를_반환한다() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.EIGHT),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE),
                new Card(Suit.DIAMOND, Rank.ACE)
        );
        Score score = new Score(new Cards(cards));

        //when
        int scoreResult = score.calculateMaxScore();

        //then
        Assertions.assertThat(scoreResult).isEqualTo(22);
    }
}
