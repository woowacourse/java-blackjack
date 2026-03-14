package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    class GetOnlyFirstHandTest {

        @Nested
        class Success {

            @Test
            void 딜러의_첫번째_카드만_담긴_손패를_반환해야_한다() {

                // given
                Dealer dealer = new Dealer();
                Card firstCard = new Card(Rank.TEN, Suit.HEART);
                Card secondCard = new Card(Rank.ACE, Suit.SPADE);
                dealer.addCard(firstCard);
                dealer.addCard(secondCard);

                // when
                var actual = dealer.getOnlyFirstHand();

                // then
                assertThat(actual)
                    .hasSize(1)
                    .containsExactly(firstCard);
            }
        }
    }

    @Nested
    class ShouldHitTest {

        @Nested
        class Success {

            @Test
            void 딜러_점수가_16_이하면_카드를_더_뽑는다() {

                // given
                Dealer dealer = new Dealer();
                dealer.addCard(new Card(Rank.TEN, Suit.HEART));
                dealer.addCard(new Card(Rank.SIX, Suit.SPADE));

                // when
                boolean actual = dealer.shouldHit();

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void 딜러_점수가_17이면_카드를_더_뽑지_않는다() {

                // given
                Dealer dealer = new Dealer();
                dealer.addCard(new Card(Rank.TEN, Suit.HEART));
                dealer.addCard(new Card(Rank.SEVEN, Suit.SPADE));

                // when
                boolean actual = dealer.shouldHit();

                // then
                assertThat(actual).isFalse();
            }

            @Test
            void 딜러_점수가_21_초과면_카드를_더_뽑지_않는다() {

                // given
                Dealer dealer = new Dealer();
                dealer.addCard(new Card(Rank.TEN, Suit.HEART));
                dealer.addCard(new Card(Rank.NINE, Suit.SPADE));
                dealer.addCard(new Card(Rank.THREE, Suit.CLOVER));

                // when
                boolean actual = dealer.shouldHit();

                // then
                assertThat(actual).isFalse();
            }
        }
    }
}
