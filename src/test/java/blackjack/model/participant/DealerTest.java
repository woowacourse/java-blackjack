package blackjack.model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    @DisplayName("카드를 더 뽑아야 하는지 판단한다")
    class JudgeShouldDraw {
        @Test
        void 점수가_일정_이하면_true를_반환한다() {
            // given
            Dealer dealer = new Dealer();

            List<Card> canDrawCards = List.of(
                    new Card(Rank.ACE, Suit.HEART),
                    new Card(Rank.TWO, Suit.HEART)
            );
            for (Card card : canDrawCards) {
                dealer.addCard(card);
            }

            // when
            boolean shouldDraw = dealer.shouldDraw();

            // then
            assertThat(shouldDraw).isTrue();
        }

        @Test
        void 점수가_일정_이상이면_false를_반환한다() {
            // given
            Dealer dealer = new Dealer();

            List<Card> cannotDrawCards = List.of(
                    new Card(Rank.JACK, Suit.HEART),
                    new Card(Rank.QUEEN, Suit.HEART)
            );
            for (Card card : cannotDrawCards) {
                dealer.addCard(card);
            }

            // when
            boolean shouldDraw = dealer.shouldDraw();

            // then
            assertThat(shouldDraw).isFalse();
        }
    }
}
