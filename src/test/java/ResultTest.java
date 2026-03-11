import domain.game.Result;
import domain.game.ResultInfo;
import domain.participant.Hand;
import domain.participant.Money;
import domain.participant.ParticipantInfo;
import domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResultTest {

    Map<Player, ResultInfo> playersResult;
    Hand hand;
    Result result;

    @BeforeEach
    void init() {
        playersResult = new HashMap<>();
        hand = new Hand();
        playersResult.put(new Player(new ParticipantInfo("user1", hand),
                new Money(30000)), ResultInfo.WIN);
        playersResult.put(new Player(new ParticipantInfo("user2", hand),
                new Money(20000)), ResultInfo.DEFEAT);
        playersResult.put(new Player(new ParticipantInfo("user3", hand),
                new Money(10000)), ResultInfo.DRAW);
        result = new Result(playersResult);
    }

    @Test
    void 딜러의_수익금은_플레이어의_수익금의_합을_음수로_만든것과_같다() {
        int expectedDealerResult = -10000;

        assertThat(result.getDealerResult()).isEqualTo(expectedDealerResult);
    }

    @Test
    void 플레이어들의_수익금을_계산할_수_있다() {
        Map<String, Integer> expectedPlayersYield = new HashMap<>();
        expectedPlayersYield.put("user1", 30000);
        expectedPlayersYield.put("user2", -20000);
        expectedPlayersYield.put("user3", 0);

        assertThat(expectedPlayersYield).isEqualTo(result.calculatePlayerYield(result.getPlayersResult()));
    }
}
