package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 가진_카드_목록으로_21을_초과하지_않는_최대합을_계산한다() {
        // given
        Cards cards = new Cards();
        cards.addAll(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.FOUR),
                new Card(Suit.SPADE, Rank.JACK))
        );
        final int expected = 19;

        // when
        int sum = cards.computeOptimalSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    void 카드_합이_항상_21을_초과할_경우_가장_작은_카드_합을_반환한다() {
        // given
        Cards cards = new Cards();
        cards.addAll(List.of(
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.SPADE, Rank.THREE),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.JACK))
        );
        final int expected = 25;

        // when
        int sum = cards.computeOptimalSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    void 카드_합이_항상_21을_초과할_경우_TRUE를_반환한다() {

        // given
        Cards cards = new Cards();
        cards.addAll(List.of(
                new Card(Suit.SPADE, Rank.JACK),
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.ACE))
        );

        // when
        final boolean isBust = cards.isBust();

        // then
        assertThat(isBust).isTrue();
    }

    @Test
    void 카드_합을_21_이하로_만들_수_있는_경우_FALSE를_반환한다() {
        // given
        Cards cards = new Cards();
        cards.addAll(List.of(
                new Card(Suit.SPADE, Rank.QUEEN),
                new Card(Suit.SPADE, Rank.TWO),
                new Card(Suit.SPADE, Rank.ACE))
        );

        // when
        final boolean isBust = cards.isBust();

        // then
        assertThat(isBust).isFalse();
    }
}
