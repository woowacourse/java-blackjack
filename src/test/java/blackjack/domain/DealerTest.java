package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.participants.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("딜러가 카드를 잘 뽑는다.")
    void drawCardTest() {
        Dealer dealer = new Dealer();
        assertThat(dealer.drawCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("딜러의 초기 핸드를 잘 세팅한다.")
    void setInitialHandTest() {
        Dealer dealer = new Dealer();
        dealer.setInitialHand();
        assertThat(dealer.getHand().size()).isEqualTo(2);
    }
}
