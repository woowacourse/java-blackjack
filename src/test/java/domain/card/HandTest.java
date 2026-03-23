package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    class CalculateTotalScoreTest {

        @Nested
        class Success {

            @Test
            void 에이스가_없으면_카드_점수의_합을_반환해야_한다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.TEN, Suit.HEART));
                hand.addCard(new Card(Rank.THREE, Suit.SPADE));

                // when
                int actual = hand.calculateTotalScore();

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
                int actual = hand.calculateTotalScore();

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
                int actual = hand.calculateTotalScore();

                // then
                assertThat(actual).isEqualTo(21);
            }
        }
    }

    @Nested
    class IsBustTest {

        @Nested
        class Success {

            @Test
            void 최종_점수가_21_초과이면_버스트이다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.THREE, Suit.HEART));
                hand.addCard(new Card(Rank.K, Suit.SPADE));
                hand.addCard(new Card(Rank.NINE, Suit.DIAMOND));

                // when
                boolean actual = hand.isBust();

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void 최종_점수가_21_이하이면_버스트가_아니다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.TWO, Suit.HEART));
                hand.addCard(new Card(Rank.K, Suit.SPADE));
                hand.addCard(new Card(Rank.NINE, Suit.DIAMOND));

                // when
                boolean actual = hand.isBust();

                // then
                assertThat(actual).isFalse();
            }
        }
    }

    @Nested
    class GetFirstCardTest {

        @Nested
        class Success {

            @Test
            void 첫번째_카드를_반환한다() {

                // given
                Hand hand = new Hand();
                Card firstCard = new Card(Rank.ACE, Suit.HEART);
                Card secondCard = new Card(Rank.K, Suit.SPADE);
                hand.addCard(firstCard);
                hand.addCard(secondCard);

                // when
                Card actual = hand.getFirstCard();

                // then
                assertThat(actual).isEqualTo(firstCard);
            }
        }
    }

    @Nested
    class GetCardTest {

        @Nested
        class Success {

            @Test
            void 손패의_전체_카드를_순서대로_반환한다() {

                // given
                Hand hand = new Hand();
                Card firstCard = new Card(Rank.ACE, Suit.HEART);
                Card secondCard = new Card(Rank.K, Suit.SPADE);
                hand.addCard(firstCard);
                hand.addCard(secondCard);

                // when
                var actual = hand.getCard();

                // then
                assertThat(actual)
                        .hasSize(2)
                        .containsExactly(firstCard, secondCard);
            }

            @Test
            void 반환한_목록은_외부에서_수정할_수_없다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.ACE, Suit.HEART));
                var cards = hand.getCard();

                // when & then
                assertThatThrownBy(() -> cards.add(new Card(Rank.K, Suit.SPADE)))
                        .isInstanceOf(UnsupportedOperationException.class);
            }
        }
    }

    @Nested
    class IsBlackjackTest {

        @Nested
        class Success {

            @Test
            void 처음_받은_2장_카드의_최종_점수가_21이면_블랙잭이다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.ACE, Suit.HEART));
                hand.addCard(new Card(Rank.K, Suit.SPADE));

                // when
                boolean actual = hand.isBlackjack();

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void 세장_이상을_받아서_최종_점수가_21이면_블랙잭이_아니다() {

                // given
                Hand hand = new Hand();
                hand.addCard(new Card(Rank.TEN, Suit.HEART));
                hand.addCard(new Card(Rank.K, Suit.SPADE));
                hand.addCard(new Card(Rank.ACE, Suit.SPADE));
                hand.addCard(new Card(Rank.ACE, Suit.SPADE));

                // when
                boolean actual = hand.isBlackjack();

                // then
                assertThat(actual).isFalse();
            }
        }
    }
}
