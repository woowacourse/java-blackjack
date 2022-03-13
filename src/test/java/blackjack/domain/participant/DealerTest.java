package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드 총합이 17보다 작을 경우 카드를 계속 받는다.")
    void under16_dealerGetCard() {
        Dealer dealer = new Dealer(List.of(Card.valueOf(TEN, SPADE), Card.valueOf(SIX, SPADE)));
        assertThat(dealer.isFinished()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 총합이 17 이상일 경우 카드를 받을 수 없다.")
    void over16_dealerCannotGetCard() {
        Dealer dealer = new Dealer(
                List.of(Card.valueOf(TEN, SPADE), Card.valueOf(SEVEN, SPADE)));
        assertThat(dealer.isFinished()).isTrue();
    }
}
