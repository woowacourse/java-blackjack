package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 플레이어는_자신이_가진_카드로_21에_최대한_가깝게_점수를_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.ACE)),
                new ScoreCalculator()
        );

        //when
        int maxScore = cards.calculateMaxScore();

        //then
        assertThat(maxScore).isEqualTo(20);
    }

    @Test
    void 플레이어의_카드에_A가_포함되어_있을_때_최솟값으로_점수를_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.KING)),
                new ScoreCalculator()
        );

        //when
        int minScore = cards.calculateMinScore();

        //then
        Assertions.assertThat(minScore).isEqualTo(11);
    }

    @Test
    void 카드가_블랙잭임을_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.ACE),
                        new Card(Suit.DIAMOND, Rank.KING)),
                new ScoreCalculator()
        );

        //when & then
        Assertions.assertThat(cards.isBlackjack()).isTrue();
    }
}
