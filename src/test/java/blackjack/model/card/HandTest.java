package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    private static final int ACE_ADJUST_VALUE = 10;

    @Nested
    class 카드를_받아서_손패에_추가한다 {
        @Test
        void 한장의_카드를_받아서_손패에_추가한다() {
            // given
            Hand hand = new Hand();
            Card card = new Card(Rank.ACE, Suit.CLUB);

            int expectedCardsCount = hand.getCards().size() + 1;

            // when
            hand = hand.addCard(card);

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
            Card card1 = new Card(Rank.TWO, Suit.CLUB);
            Card card2 = new Card(Rank.THREE, Suit.CLUB);

            hand = hand.addCard(card1);
            hand = hand.addCard(card2);

            int expectedScore = card1.getScore() + card2.getScore();

            // when
            int actualScore = hand.calculateScore();

            // then
            assertThat(actualScore).isEqualTo(expectedScore);
        }

        @Test
        void 에이스가_존재할_때_점수를_조정해도_버스트이지_않다면_점수를_조정한다() {
            // given
            Hand hand = new Hand();
            Card card1 = new Card(Rank.ACE, Suit.HEART);
            Card card2 = new Card(Rank.TWO, Suit.HEART);

            hand = hand.addCard(card1);
            hand = hand.addCard(card2);

            int expectedScore = card1.getScore() + card2.getScore() + ACE_ADJUST_VALUE;

            // when
            int actualScore = hand.calculateScore();

            // then
            assertThat(actualScore).isEqualTo(expectedScore);
        }

        @Test
        void 에이스가_존재할_때_점수를_조정하면_버스트라면_점수를_조정하지_않는다() {
            // given
            List<Card> bustCards = List.of(
                    new Card(Rank.KING, Suit.HEART),
                    new Card(Rank.QUEEN, Suit.HEART),
                    new Card(Rank.JACK, Suit.HEART)
            );
            Hand hand = new Hand(bustCards);

            int expectedScore = bustCards.stream()
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

            hand = hand.addCard(new Card(Rank.JACK, Suit.DIAMOND));
            hand = hand.addCard(new Card(Rank.QUEEN, Suit.DIAMOND));
            hand = hand.addCard(new Card(Rank.KING, Suit.DIAMOND));

            // when
            boolean bust = hand.isBust();

            // then
            assertThat(bust).isTrue();
        }

        @Test
        void 카드_점수_합이_일정_이하라면_버스트가_아니라고_판단한다() {
            // given
            Hand hand = new Hand();

            hand = hand.addCard(new Card(Rank.ACE, Suit.DIAMOND));
            hand = hand.addCard(new Card(Rank.TWO, Suit.DIAMOND));
            hand = hand.addCard(new Card(Rank.THREE, Suit.DIAMOND));

            // when
            boolean bust = hand.isBust();

            // then
            assertThat(bust).isFalse();
        }
    }
}
