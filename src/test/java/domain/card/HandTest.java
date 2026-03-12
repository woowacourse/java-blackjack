package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HandTest {

    @Nested
    class AddCardTest {

        @Nested
        class Success {

            @Test
            void 카드를_추가하면_손패에_카드가_저장되어야_한다() {

                // given
                Hand hand = new Hand();
                Card card = new Card(Rank.ACE, Suit.HEART);

                // when
                hand.addCard(card);

                // then
                assertThat(hand.getCard())
                    .hasSize(1)
                    .containsExactly(card);
            }

            @Test
            void 카드를_여러장_추가하면_추가한_순서대로_손패에_저장되어야_한다() {

                // given
                Hand hand = new Hand();
                Card firstCard = new Card(Rank.ACE, Suit.HEART);
                Card secondCard = new Card(Rank.K, Suit.SPADE);

                // when
                hand.addCard(firstCard);
                hand.addCard(secondCard);

                // then
                assertThat(hand.getCard())
                    .hasSize(2)
                    .containsExactly(firstCard, secondCard);
            }
        }
    }

    @Nested
    class CalculateScoreTest {

        @Nested
        class Success {

            @Test
            void 에이스가_없으면_카드_점수의_합을_반환해야_한다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.TEN, Suit.HEART));
                hand.addCard(new Card(Rank.THREE, Suit.SPADE));

                // when
                int actual = hand.calculateScore();

                // then
                assertThat(actual).isEqualTo(13);
            }

            @Test
            void 에이스가_있고_21을_초과하면_에이스를_1점으로_계산해야_한다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.ACE, Suit.HEART));
                hand.addCard(new Card(Rank.K, Suit.SPADE));
                hand.addCard(new Card(Rank.THREE, Suit.DIAMOND));

                // when
                int actual = hand.calculateScore();

                // then
                assertThat(actual).isEqualTo(14);
            }

            @Test
            void 에이스가_여러장이면_가능한_최적의_점수로_계산해야_한다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.ACE, Suit.HEART));
                hand.addCard(new Card(Rank.ACE, Suit.SPADE));
                hand.addCard(new Card(Rank.NINE, Suit.DIAMOND));

                // when
                int actual = hand.calculateScore();

                // then
                assertThat(actual).isEqualTo(21);
            }
        }
    }

    @Nested
    class GetCardTest {

        @Nested
        class Success {

            @Test
            void 손패가_비어있다면_빈_목록을_반환해야_한다() {

                // given
                Hand hand = new Hand();

                // when
                var actual = hand.getCard();

                // then
                assertThat(actual).isEmpty();
            }

            @Test
            void 손패의_카드들을_이름_목록으로_반환해야_한다() {

                // given
                Hand hand = new Hand();
                Card firstCard = new Card(Rank.FIVE, Suit.HEART);
                Card secondCard = new Card(Rank.J, Suit.DIAMOND);
                hand.addCard(firstCard);
                hand.addCard(secondCard);

                // when
                var actual = hand.getCard();

                // then
                assertThat(actual)
                    .hasSize(2)
                    .containsExactly(firstCard, secondCard);
            }
        }
    }
}
