package blackjack.domain.betting;

import static blackjack.domain.TestCardFixture.aceCard;
import static blackjack.domain.TestCardFixture.sevenCard;
import static blackjack.domain.TestCardFixture.tenCard;
import static org.assertj.core.api.Assertions.*;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingPlayerTest {

    @DisplayName("BettingPlayer가 정상적으로 생성되는지 확인")
    @Test
    void create() {
        BettingPlayer seung = BettingPlayer.of(new Player("seung"), "10000");

        assertThat(seung.getName()).isEqualTo("seung");
    }

    @DisplayName("수익을 정상적으로 계산하는지 확인")
    @Test
    void calculateProfit() {
        Player player = new Player("seung");
        BettingPlayer seung = BettingPlayer.of(player, "10000");
        player.hit(aceCard);
        player.hit(tenCard);

        Dealer dealer = new Dealer();
        dealer.hit(tenCard);
        dealer.hit(sevenCard);

        assertThat(seung.calculateProfit(dealer)).isEqualTo(15000);
    }
}
