package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameAccountTest {

    @Test
    @DisplayName("플레이어가 금액을 배팅한다.")
    void betMoneyTest() {
        GameAccount gameAccount = new GameAccount();
        Player player = new Player(new Name("loki"));
        Money money = new Money(50000);

        gameAccount.betMoney(player, money);

        assertThat(gameAccount.findMoney(player)).isEqualTo(new Money(50000));
    }
}
