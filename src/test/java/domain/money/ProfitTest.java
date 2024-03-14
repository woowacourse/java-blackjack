package domain.money;

import domain.participant.Dealer;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {
    @DisplayName("딜러는 10000 만큼 수익을 얻는다.")
    @Test
    void earnProfit() {
        Dealer dealer = new Dealer();
        dealer.earn(new Money(10000));
        Assertions.assertThat(dealer.totalProfit()).isEqualTo(10000);
    }

    @DisplayName("참가자는 10000만큼 수익을 잃는다.")
    @Test
    void loseProfit() {
        Player player = new Player("pobi");
        player.lose(new Money(10000));
        Assertions.assertThat(player.totalProfit()).isEqualTo(-10000);
    }
}
