package domain.game;

import domain.participant.Hand;
import domain.participant.player.Money;
import domain.participant.player.PlayerInfo;
import domain.participant.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultTest {

    Map<Player, Outcome> playersResult;
    Hand hand;
    GameResult gameResult;

    @BeforeEach
    void init() {
        playersResult = new HashMap<>();
        hand = new Hand();
        playersResult.put(new Player(new PlayerInfo("user1",
                new Money(BigDecimal.valueOf(30000)))), Outcome.WIN);
        playersResult.put(new Player(new PlayerInfo("user1",
                new Money(BigDecimal.valueOf(20000)))), Outcome.DEFEAT);
        playersResult.put(new Player(new PlayerInfo("user1",
                new Money(BigDecimal.valueOf(10000)))), Outcome.PUSH);
        gameResult = new GameResult(playersResult);
    }

    @Test
    void 딜러의_수익금은_플레이어의_수익금의_합을_음수로_만든것과_같다() {
        BigDecimal expectedDealerResult = BigDecimal.valueOf(-10000);

        assertThat(gameResult.getDealerResult()).isEqualByComparingTo(expectedDealerResult);
    }

    @Test
    void 플레이어들의_수익금을_계산할_수_있다() {
        Map<String, BigDecimal> expectedPlayersYield = new HashMap<>();
        expectedPlayersYield.put("user1", BigDecimal.valueOf(30000));
        expectedPlayersYield.put("user2", BigDecimal.valueOf(-20000));
        expectedPlayersYield.put("user3", BigDecimal.ZERO);

        expectedPlayersYield.forEach((name, expectedValue) -> {
            assertThat(expectedPlayersYield.get(name))
                    .as("%s의 수익금이 일치하지 않습니다", name)
                    .isEqualByComparingTo(expectedValue);
        });
    }
}
