package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    static final int ACE_ADJUST_VALUE = 10;

    @Nested
    class 카드를_받아서_손패에_추가한다 {
        @Test
        void 한장의_카드를_받아서_손패에_추가한다() {
            // given
            Hand hand = new Hand();
            Card card = new Card(Rank.ACE, Suit.CLUB);

            int expectedCardsCount = hand.getCards().size() + 1;

            // when
            hand.hit(card);

            // then
            int actualCardsCount = hand.getCards().size();
            assertThat(actualCardsCount).isEqualTo(expectedCardsCount);
        }

        @Test
        void 여러장의_카드를_받아서_손패에_추가한다() {
            // given
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    new Card(Rank.ACE, Suit.CLUB),
                    new Card(Rank.TWO, Suit.CLUB),
                    new Card(Rank.THREE, Suit.CLUB)
            );
            int expectedCardsCount = hand.getCards().size() + cards.size();

            // when
            hand.firstDeal(cards);

            // then
            int actualCardsCount = hand.getCards().size();
            assertThat(actualCardsCount).isEqualTo(expectedCardsCount);
        }
    }

    @Nested
    class 카드_점수의_합을_계산한다 {
        @Test
        void 에이스가_아닌_카드들의_점수는_단순히_합한다() {
            // given
            Hand hand = new Hand();
            List<Card> cards = List.of(
                    new Card(Rank.TWO, Suit.CLUB),
                    new Card(Rank.THREE, Suit.CLUB)
            );
            hand.firstDeal(cards);

            int expectedScore = cards.stream()
                    .mapToInt(Card::getScore)
                    .sum();

            // when
            int actualScore = hand.calculateScore();

            // then
            assertThat(actualScore).isEqualTo(expectedScore);
        }

        @Test
        void 에이스가_존재할_때_점수를_조정해도_버스트이지_않다면_점수를_조정한다() {
            // given
            Hand hand = new Hand();
            List<Card> notBustCards = List.of(
                    new Card(Rank.ACE, Suit.HEART),
                    new Card(Rank.TWO, Suit.HEART)
            );
            hand.firstDeal(notBustCards);

            int expectedScore = notBustCards.stream()
                    .mapToInt(Card::getScore)
                    .sum()
                    + ACE_ADJUST_VALUE;

            // when
            int actualScore = hand.calculateScore();

            // then
            assertThat(actualScore).isEqualTo(expectedScore);
        }

        @Test
        void 에이스가_존재할_때_점수를_조정하면_버스트라면_점수를_조정하지_않는다() {
            // given
            Hand hand = new Hand();
            List<Card> notBustCards = List.of(
                    new Card(Rank.ACE, Suit.HEART),
                    new Card(Rank.QUEEN, Suit.HEART),
                    new Card(Rank.JACK, Suit.HEART)
            );
            hand.firstDeal(notBustCards);

            int expectedScore = notBustCards.stream()
                    .mapToInt(Card::getScore)
                    .sum();

            // when
            int actualScore = hand.calculateScore();

            // then
            assertThat(actualScore).isEqualTo(expectedScore);
        }
    }

    @Nested
    class 버스트_여부를_판단한다 {
        @Test
        void 카드_점수_합이_일정_이상이면_버스트로_판단한다() {
            // given
            Hand hand = new Hand();
            List<Card> bustedCards = List.of(
                    new Card(Rank.JACK, Suit.DIAMOND),
                    new Card(Rank.QUEEN, Suit.DIAMOND),
                    new Card(Rank.KING, Suit.DIAMOND)
            );
            hand.firstDeal(bustedCards);

            // when
            boolean bust = hand.isBust();

            // then
            assertThat(bust).isTrue();
        }

        @Test
        void 카드_점수_합이_일정_이하라면_버스트가_아니라고_판단한다() {
            // given
            Hand hand = new Hand();
            List<Card> notBustedCards = List.of(
                    new Card(Rank.ACE, Suit.DIAMOND),
                    new Card(Rank.TWO, Suit.DIAMOND),
                    new Card(Rank.THREE, Suit.DIAMOND)
            );
            hand.firstDeal(notBustedCards);

            // when
            boolean bust = hand.isBust();

            // then
            assertThat(bust).isFalse();
        }
    }
}
