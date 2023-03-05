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
        // given & when
        PlayerCards playerCards = new PlayerCards();
        playerCards.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        playerCards.updateCardScore(new Card(CardNumber.FIVE, CardSymbol.HEART));
        Dealer dealer = new Dealer(new Name("딜러"));

        // then
        Assertions.assertThat(dealer.isUnderLimit()).isTrue();
    }
}
