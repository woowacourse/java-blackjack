package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러를_생성한다() {
        // given
        Dealer dealer = new Dealer();

        // when & then
        assertThat(dealer).isNotNull();
    }

    @Test
    void 딜러에게_카드를_한장_준다() {
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        assertThat(dealer.getReceivedCards().size()).isEqualTo(1);
    }
}
