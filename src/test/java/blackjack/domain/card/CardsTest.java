package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Stack;
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

    @Test
    void 카드가_블랙잭이_아님을_계산할_수_있다() {
        //given
        Cards cards = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.TEN),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.DIAMOND, Rank.ONE)),
                new ScoreCalculator()
        );

        //when & then
        Assertions.assertThat(cards.isBlackjack()).isFalse();
    }


    @Test
    void 한_번에_최대_2개의_카드만_뽑을_수_있다() {
        //given
        Stack<Card> cards = new Stack<>();
        Cards cardDeck = new Cards(cards, new ScoreCalculator());

        //when & then
        Assertions.assertThatThrownBy(() -> cardDeck.take(
                        new Card(Suit.CLUB, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.ONE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드는 한 번에 최대 두 장까지 받을 수 있습니다.");
    }
}
