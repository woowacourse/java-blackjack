package blackjack.domain.game;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerMoneyTest {

    @Test
    @DisplayName("플레이어 승패에 대한 수익금을 반환한다")
    void betMoney() {
        Player blackJack = new Player(new Name("bj"));
        Player win = new Player(new Name("win"));
        Player draw = new Player(new Name("draw"));
        Player lose = new Player(new Name("lose"));

        PlayerMoney playerMoney = new PlayerMoney();
        playerMoney.addPlayerMoney(blackJack, new Money(10000));
        playerMoney.addPlayerMoney(win, new Money(10000));
        playerMoney.addPlayerMoney(draw, new Money(10000));
        playerMoney.addPlayerMoney(lose, new Money(10000));

        Map<Player, Result> results = new HashMap<>();
        results.put(blackJack, Result.BLACKJACK);
        results.put(win, Result.WIN);
        results.put(draw, Result.DRAW);
        results.put(lose, Result.LOSE);

        PlayerMoney resultPlayerMoney = playerMoney.calculateYieldAllPlayer(results);
        Map<Player, Money> result = resultPlayerMoney.getPlayerMoney();

        Assertions.assertThat(result.get(blackJack)).isEqualTo(new Money(15000));
        Assertions.assertThat(result.get(win)).isEqualTo(new Money(10000));
        Assertions.assertThat(result.get(draw)).isEqualTo(new Money(0));
        Assertions.assertThat(result.get(lose)).isEqualTo(new Money(-10000));
    }
}
