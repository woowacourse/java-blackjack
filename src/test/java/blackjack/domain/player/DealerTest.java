package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("isNotOverSeventeen()은 점수가 17이 안넘으면 true를 반환한다.")
    void total_score_over_17() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.addCard(new Card(CardNumber.THREE, CardSymbol.HEART));
        dealer.addCard(new Card(CardNumber.TWO, CardSymbol.HEART));

        // then
        Assertions.assertThat(dealer.isUnderLimit()).isTrue();
    }

    @Test
    @DisplayName("isNotOverSeventeen()은 점수가 17이 안넘으면 true를 반환한다.")
    void total_score_over_17111() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.addCard(new Card(CardNumber.JACK, CardSymbol.HEART));
        dealer.addCard(new Card(CardNumber.KING, CardSymbol.HEART));

        // then
        Assertions.assertThat(dealer.isUnderLimit()).isFalse();
    }
}
