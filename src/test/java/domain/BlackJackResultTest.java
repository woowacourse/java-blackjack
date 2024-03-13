package domain;

import domain.constant.GamerResult;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackResultTest {
    @Test
    @DisplayName("Player들의 승리 결과를 반환한다.")
    void getPlayersResult() {
        Name win = new Name("win");
        Name lose = new Name("lose");
        BlackJackResult blackJackResult = new BlackJackResult(20, Map.of(
                lose, 10,
                win, 21));
        Map<Name, GamerResult> playersResult = blackJackResult.getPlayersResult();
        Assertions.assertThat(playersResult)
                .isEqualTo(Map.of(
                        win, GamerResult.WIN,
                        lose, GamerResult.LOSE));
    }

    @Test
    @DisplayName("Dealer의 승리 결과를 반환한다.")
    void getDealerResult() {
        BlackJackResult blackJackResult = new BlackJackResult(20, Map.of(
                new Name("test"), 10,
                new Name("test2"), 10,
                new Name("test3"), 21));
        Map<GamerResult, Integer> dealerResult = blackJackResult.getDealerResult();
        Assertions.assertThat(dealerResult)
                .isEqualTo(Map.of(
                        GamerResult.WIN, 2,
                        GamerResult.LOSE, 1));
    }
}
