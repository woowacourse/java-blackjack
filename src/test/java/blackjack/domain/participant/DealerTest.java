package blackjack.domain.participant;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @Nested
    class isDrawable_메서드는 {

        @Test
        void 카드가_2장_이하이고_점수가_16점_이하라면_true_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(SIX, HEART));
            //16점

            assertThat(dealer.isDrawable()).isTrue();
        }

        @Test
        void 카드가_2장_이하이고_점수가_16점_초과라면_false_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(SEVEN, HEART));
            //17점

            assertThat(dealer.isDrawable()).isFalse();
        }

        @Test
        void 카드가_2장_초과라면_false_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(TWO, CLOVER));
            dealer.drawCard(new Card(SIX, HEART));
            dealer.drawCard(new Card(SEVEN, DIAMOND));
            //15점

            assertThat(dealer.isDrawable()).isFalse();
        }
    }

    @Nested
    class drawCard_메서드는 {

        @Test
        void 카드를_받을_수_없는_상태라면_예외를_던진다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(ACE, HEART));
            //21점

            assertThatThrownBy(() -> dealer.drawCard(new Card(TWO, DIAMOND))).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 카드를_받을_수_있는_상태라면_카드를_받는다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(QUEEN, CLOVER));
            dealer.drawCard(new Card(SIX, HEART));
            //17점

            dealer.drawCard(new Card(TWO, DIAMOND));
            //19점

            assertThat(dealer.isDrawable()).isFalse();
        }
    }

    @Test
    void 점수를_확인한다() {
        final Dealer dealer = new Dealer();
        dealer.drawCard(new Card(TWO, CLOVER));
        dealer.drawCard(new Card(SIX, HEART));
        dealer.drawCard(new Card(SEVEN, DIAMOND));
        //15점

        assertThat(dealer.getScore()).isEqualTo(15);
    }

    @Test
    void 딜러는_딜러이다() {
        final Dealer dealer = new Dealer();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    void 딜러의_이름은_딜러이다() {
        final Dealer dealer = new Dealer();

        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    void 딜러가_카드를_뽑을_수_있는_최대_점수는_16점이다() {
        final Dealer dealer = new Dealer();

        assertThat(dealer.getMaximumDrawableScore()).isEqualTo(16);
    }

    @Nested
    class isBlackJack_메서드는 {

        @Test
        void 카드가_2장이고_점수가_21점이면_true_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(ACE, HEART));
            dealer.drawCard(new Card(QUEEN, DIAMOND));
            //21점

            assertThat(dealer.isBlackJack()).isTrue();
        }

        @Test
        void 카드가_3장_이상이고_점수가_21점이면_false_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(SEVEN, HEART));
            dealer.drawCard(new Card(NINE, DIAMOND));
            dealer.drawCard(new Card(FIVE, DIAMOND));
            //21점

            assertThat(dealer.isBlackJack()).isFalse();
        }

        @Test
        void 카드가_2장이고_점수가_21점이_아니면_false_반환한다() {
            final Dealer dealer = new Dealer();
            dealer.drawCard(new Card(SEVEN, HEART));
            dealer.drawCard(new Card(NINE, DIAMOND));
            //16점

            assertThat(dealer.isBlackJack()).isFalse();
        }
    }

    @Test
    void 딜러의_마지막_카드는_확인할_수_없다() {
        final Dealer dealer = new Dealer();
        dealer.drawCard(new Card(SEVEN, HEART));
        dealer.drawCard(new Card(NINE, DIAMOND));

        final Hand hand = dealer.getHiddenHand();

        assertThat(hand.count()).isEqualTo(1);
    }
}
