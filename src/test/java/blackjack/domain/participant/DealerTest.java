package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러가 보유한 숫자의 합이 16초과인 경우 false를 반환한다.")
    void checkUpperSumStandardTest() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardNumber.EIGHT, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(dealer.checkUnderSumStandard()).isFalse();
    }

    @Test
    @DisplayName("딜러가 보유한 숫자의 합이 16이하인 경우 true를 반환한다.")
    void checkUnderSumStandard() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardNumber.SIX, CardType.CLOVER));
        dealer.receiveCard(new Card(CardNumber.QUEEN, CardType.CLOVER));

        assertThat(dealer.checkUnderSumStandard()).isTrue();
    }
}
