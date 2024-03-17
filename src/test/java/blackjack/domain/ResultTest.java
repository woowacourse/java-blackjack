package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.game.Result;
import blackjack.domain.participant.Betting;
import blackjack.domain.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("결과를 생성한다")
    @Test
    void create() {
        assertThatCode(() -> new Result(Map.of(new Player("mark", new Betting(1000)), 1500)))
                .doesNotThrowAnyException();
    }

    @DisplayName("딜러의 수익을 반환한다")
    @Test
    void dealerResult() {
        Result result = new Result(
                Map.of(new Player("mark", new Betting(1000)), 0,
                        new Player("pk", new Betting(1000)), -1000,
                        new Player("isang", new Betting(1000)), 1000));

        int dealerEarning = result.calculateDealerEarning();

        assertThat(dealerEarning).isEqualTo(0);
    }

}
