package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        this.dealer = new Dealer();
    }

    @Test
    @DisplayName("Bank에 플레이어의 배팅 금액을 저장한다.")
    void saveBettingMoney() {
        final Player player = new Player("player1");
        final Money money = new Money(1000);

        dealer.saveBettingMoney(player, money);

        assertThat(dealer.getBank().findMoney(player)).isEqualTo(new Money(1000));
    }
}
