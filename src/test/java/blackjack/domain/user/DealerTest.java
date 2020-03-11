package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("딜러가 갖고 있는 카드의 합이 16 이하인지 확인")
    void shouldReceiveCard1() {
        Dealer dealer = new Dealer();
        dealer.add(new Card(CardSymbol.ACE, CardType.SPADE));
        assertThat(dealer.shouldDrawCard()).isTrue();
    }

    @Test
    @DisplayName("딜러가 갖고 있는 카드의 합이 16 이하인지 확인")
    void shouldReceiveCard2() {
        Dealer dealer = new Dealer();
        dealer.add(new Card(CardSymbol.ACE, CardType.SPADE));
        dealer.add(new Card(CardSymbol.TEN, CardType.SPADE));
        dealer.add(new Card(CardSymbol.QUEEN, CardType.SPADE));
        assertThat(dealer.shouldDrawCard()).isFalse();
    }
}