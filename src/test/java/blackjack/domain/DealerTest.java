package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.GamerInformation.GamblingMoney;
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

    @Test
    @DisplayName("딜러가 돈을 벌었다.")
    void gainMoneyTest() {
        Dealer dealer = new Dealer();
        dealer.gainMoney(new GamblingMoney(3000));
        assertThat(dealer.getGamblingMoney()).isEqualTo(new GamblingMoney(3000));
    }
}
