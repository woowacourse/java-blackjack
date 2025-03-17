package card;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class HandCardsTest {

    @Test
    void 가진_카드_목록으로_21을_초과하지_않는_최대합을_계산한다() {
        // given
        HandCards handCards = new HandCards();
        handCards.addAll(List.of(
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.THREE),
                new Card(CardSuit.SPADE, CardRank.FOUR),
                new Card(CardSuit.SPADE, CardRank.JACK))
        );
        final int expected = 19;

        // when
        int sum = handCards.computeOptimalSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    void 카드_합이_항상_21을_초과할_경우_가장_작은_카드_합을_반환한다() {
        // given
        HandCards handCards = new HandCards();
        handCards.addAll(List.of(
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.THREE),
                new Card(CardSuit.SPADE, CardRank.QUEEN),
                new Card(CardSuit.SPADE, CardRank.JACK))
        );
        final int expected = 25;

        // when
        int sum = handCards.computeOptimalSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }

    @Test
    void 카드_합이_항상_21을_초과할_경우_TRUE를_반환한다() {
        // given
        HandCards handCards = new HandCards();
        handCards.addAll(List.of(
                new Card(CardSuit.SPADE, CardRank.JACK),
                new Card(CardSuit.SPADE, CardRank.QUEEN),
                new Card(CardSuit.SPADE, CardRank.TWO))
        );

        // when
        final boolean isBurst = handCards.isBust();

        // then
        assertThat(isBurst).isTrue();
    }

    @Test
    void 카드_합을_21_이하로_만들_수_있는_경우_FALSE를_반환한다() {
        // given
        HandCards handCards = new HandCards();
        handCards.addAll(List.of(
                new Card(CardSuit.SPADE, CardRank.QUEEN),
                new Card(CardSuit.SPADE, CardRank.ACE),
                new Card(CardSuit.SPADE, CardRank.JACK))
        );

        // when
        final boolean isBurst = handCards.isBust();

        // then
        assertThat(isBurst).isFalse();
    }

    @Test
    void 블랙잭인_경우_TRUE를_반환한다() {
        // given
        HandCards handCards = new HandCards();
        handCards.addAll(List.of(
                new Card(CardSuit.SPADE, CardRank.QUEEN),
                new Card(CardSuit.SPADE, CardRank.ACE))
        );

        // when
        final boolean isBlackjack = handCards.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }

    @Test
    void 블랙잭인이_아닌_경우_FALSE를_반환한다() {
        // given
        HandCards handCards = new HandCards();
        handCards.addAll(List.of(
                new Card(CardSuit.SPADE, CardRank.THREE),
                new Card(CardSuit.SPADE, CardRank.ACE))
        );

        // when
        final boolean isBlackjack = handCards.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }
}
