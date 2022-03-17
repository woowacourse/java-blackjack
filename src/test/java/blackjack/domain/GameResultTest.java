package blackjack.domain;

import blackjack.domain.card.*;
import blackjack.domain.entry.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @Test
    @DisplayName("딜러 숫자보다 플레이어 숫자가 큰 경우 베팅 금액을 갖는다.")
    void winTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        Player player = new Player("플레이어", HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));
        player.initBettingMoney(10000);
        result.put(PlayerOutcome.WIN, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getBettingMoney(player, PlayerOutcome.WIN)).isEqualTo(10000);
    }

    @Test
    @DisplayName("딜러 숫자보다 플레이어 숫자가 작은 경우 베팅 금액을 잃는다.")
    void loseTest() {
        Map<PlayerOutcome, List<Player>> result = new HashMap<>();
        Player player = new Player("플레이어", HoldCards.init(List.of(Card.valueOf(Suit.HEART, Denomination.EIGHT), Card.valueOf(Suit.CLUB, Denomination.EIGHT))));
        player.initBettingMoney(10000);
        result.put(PlayerOutcome.WIN, List.of(player));

        GameResult gameResult = new GameResult(result);

        assertThat(gameResult.getBettingMoney(player, PlayerOutcome.LOSE)).isEqualTo(-10000);
    }
}
