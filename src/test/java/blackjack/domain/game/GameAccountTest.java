package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamer.GameResult;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameAccountTest {

    private GameAccount gameAccount;
    private Player player;
    private Money money;

    @BeforeEach
    void setUp() {
        gameAccount = new GameAccount();
        player = new Player(new Name("loki"));
        money = new Money(50000);
    }

    @Test
    @DisplayName("플레이어가 금액을 배팅한다.")
    void betMoneyTest() {
        gameAccount.betMoney(player, money);

        assertThat(gameAccount.findMoney(player)).isEqualTo(new Money(50000));
    }

    @Test
    @DisplayName("플레이어의 게임 결과를 배팅한 금액에 적용한다")
    void applyGameResultsTest() {
        Map<Player, GameResult> gameResults = new LinkedHashMap<>();
        gameAccount.betMoney(player, money);
        gameResults.put(player, GameResult.BLACKJACK_WIN);

        gameAccount.applyGameResults(gameResults);

        assertThat(gameAccount.findMoney(player)).isEqualTo(new Money(75000));
    }
}
