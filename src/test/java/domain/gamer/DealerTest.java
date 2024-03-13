package domain.gamer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 처음에 한장을 받는다.")
    @Test
    void initCardTest() {
        Dealer dealer = new Dealer();

        Assertions.assertThat(dealer.cards.getSize()).isEqualTo(1);
    }

    @DisplayName("receiveCard 메서드를 호출하면 딜러가 카드를 받는다")
    @Test
    void receiveCardTest() {
        Dealer dealer = new Dealer();
        dealer.receiveCard();

        Assertions.assertThat(dealer.cards.getSize()).isEqualTo(2);
    }

    @DisplayName("딜러 카드의 점수가 16이하 이면 shouldDraw 는 True를 리턴한다")
    @Test
    void shouldDrawTest() {
        Dealer dealer = new Dealer();

        Assertions.assertThat(dealer.shouldDraw()).isTrue();
    }
}
