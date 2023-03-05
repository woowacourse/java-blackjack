package blackjack.domain.participant;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.SEVEN;
import static blackjack.domain.card.Number.SIX;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
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
            final Cards cards = new Cards(List.of(
                    new Card(QUEEN, CLOVER),
                    new Card(SIX, HEART)
            )); //16점
            final Dealer dealer = new Dealer(cards);

            assertThat(dealer.isDrawable()).isTrue();
        }

        @Test
        void 카드가_2장_이하이고_점수가_16점_초과라면_false_반환한다() {
            final Cards cards = new Cards(List.of(
                    new Card(QUEEN, CLOVER),
                    new Card(SEVEN, HEART)
            )); //17점
            final Dealer dealer = new Dealer(cards);

            assertThat(dealer.isDrawable()).isFalse();
        }

        @Test
        void 카드가_2장_초과라면_false_반환한다() {
            final Cards cards = new Cards(List.of(
                    new Card(TWO, CLOVER),
                    new Card(SIX, HEART),
                    new Card(SEVEN, DIAMOND)
            )); // 15점
            final Dealer dealer = new Dealer(cards);

            assertThat(dealer.isDrawable()).isFalse();
        }
    }

    @Nested
    class drawCard_메서드는 {

        @Test
        void 카드를_받을_수_없는_상태라면_예외를_던진다() {
            final List<Card> cardPack = new ArrayList<>(List.of(
                    new Card(QUEEN, CLOVER),
                    new Card(ACE, HEART)
            ));
            final Cards cards = new Cards(cardPack);
            final Dealer dealer = new Dealer(cards);

            assertThatThrownBy(() -> dealer.drawCard(new Card(TWO, DIAMOND)))
                    .isInstanceOf(IllegalStateException.class);
        }

        @Test
        void 카드를_받을_수_있는_상태라면_카드를_받는다() {
            final List<Card> cardPack = new ArrayList<>(List.of(
                    new Card(QUEEN, CLOVER),
                    new Card(SIX, HEART)
            ));
            final Cards cards = new Cards(cardPack);
            final Dealer dealer = new Dealer(cards);

            dealer.drawCard(new Card(TWO, DIAMOND));

            assertThat(dealer.isDrawable()).isFalse();
        }
    }

    @Test
    void 카드를_받는다() {
        final List<Card> cardPack = new ArrayList<>(List.of(
                new Card(QUEEN, CLOVER),
                new Card(SIX, HEART)
        ));
        final Cards cards = new Cards(cardPack);
        final Dealer dealer = new Dealer(cards);

        dealer.drawCard(new Card(ACE, DIAMOND));

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 점수를_확인한다() {
        final Cards cards = new Cards(List.of(
                new Card(TWO, CLOVER),
                new Card(SIX, HEART),
                new Card(SEVEN, DIAMOND)
        ));
        final Dealer dealer = new Dealer(cards);

        assertThat(dealer.getScore().getValue()).isEqualTo(15);
    }
}
