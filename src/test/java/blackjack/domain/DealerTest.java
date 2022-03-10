package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("추가 카드가 필요한 경우 참을 반환한다")
    void needMoreCardWhenTrue() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.HEART, Denomination.TWO));

        assertThat(dealer.needMoreCard()).isTrue();
    }

    @Test
    @DisplayName("추가 카드가 필요없는 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.HEART, Denomination.NINE));
        dealer.addCard(new Card(Symbol.SPADE, Denomination.NINE));

        assertThat(dealer.needMoreCard()).isFalse();
    }
}
