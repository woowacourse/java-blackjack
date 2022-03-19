package blackjack.domain.participant;

import static blackjack.domain.Fixture.TEN_SPADES;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardSymbol.SPADES;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드 총합이 17보다 작을 경우 카드를 계속 받는다.")
    void under16_dealerGetCard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(TEN_SPADES);
        dealer.receiveCard(Card.valueOf(SIX, SPADES));
        assertThat(dealer.isFinished()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 총합이 17 이상일 경우 카드를 받을 수 없다.")
    void over16_dealerCannotGetCard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(TEN_SPADES);
        dealer.receiveCard(Card.valueOf(SEVEN, SPADES));
        assertThat(dealer.isFinished()).isTrue();
    }
}
