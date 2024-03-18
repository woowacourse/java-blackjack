package domain;

import domain.constant.GamerResult;
import domain.dto.HandStatus;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultTest {
    @Test
    @DisplayName("Player들의 승리 결과를 반환한다.")
    void getPlayersResult() {
        BlackJackResult blackJackResult = new BlackJackResult(new HandStatus(20, 3), Map.of(
                "test", new HandStatus(10, 3),
                "test2", new HandStatus(21, 3)));
        Map<String, GamerResult> playersResult = blackJackResult.getPlayersResult();
        Assertions.assertThat(playersResult)
                .isEqualTo(Map.of(
                        "test", GamerResult.LOSE,
                        "test2", GamerResult.WIN));
    }

    @Test
    @DisplayName("Dealer의 승리 결과를 반환한다.")
    void getDealerResult() {
        BlackJackResult blackJackResult = new BlackJackResult(new HandStatus(20, 3), Map.of(
                "test", new HandStatus(10, 3),
                "test2", new HandStatus(21, 3)));
        Map<GamerResult, Integer> dealerResult = blackJackResult.getDealerResult();
        Assertions.assertThat(dealerResult)
                .isEqualTo(Map.of(
                        GamerResult.WIN, 1,
                        GamerResult.LOSE, 1));
    }
}
