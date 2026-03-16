package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 합이 16 이하라면 카드를 더 뽑는다.")
    void 딜러는_카드_합이_16이하라면_draw한다() {
        // given
        final Dealer dealer = new Dealer();
        dealer.draw(new Card(CardSuit.SPADE, CardRank.TEN));
        dealer.draw(new Card(CardSuit.SPADE, CardRank.SIX));

        // when
        final boolean result = dealer.isDrawable();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드 합이 17 이상이면 카드를 뽑지 않는다.")
    void 딜러는_카드_합이_17이상이면_draw하지_않는다() {
        // given
        final Dealer dealer = new Dealer();
        dealer.draw(new Card(CardSuit.SPADE, CardRank.TEN));
        dealer.draw(new Card(CardSuit.SPADE, CardRank.SEVEN));

        // when
        final boolean result = dealer.isDrawable();

        // then
        assertThat(result).isFalse();
    }
}
