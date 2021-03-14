package blackjack.domain.dto;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Player;
import blackjack.domain.gametable.Outcome;
import blackjack.dto.PlayerResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultTest {

    @Test
    void getName() {
        final Player player = new Player("better", new BettingMoney("10000"));

        final PlayerResult playerResult = new PlayerResult(player, Outcome.WIN);
        assertThat(playerResult.getName()).isEqualTo("better");
    }

    @Test
    @DisplayName("이겼을 경우")
    void win_case() {
        final Player player = new Player(new BettingMoney("10000"));

        final PlayerResult playerResult = new PlayerResult(player, Outcome.WIN);

        assertThat(playerResult.getReturnMoney()).isEqualTo(new BettingMoney(15000));
        assertThat(playerResult.getOutcome()).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("비겼을 경우")
    void draw_case() {
        final Player player = new Player(new BettingMoney("10000"));

        final PlayerResult playerResult = new PlayerResult(player, Outcome.DRAW);

        assertThat(playerResult.getReturnMoney()).isEqualTo(new BettingMoney(10000));
        assertThat(playerResult.getOutcome()).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("졌을 경우")
    void lose_case() {
        final Player player = new Player(new BettingMoney("10000"));

        final PlayerResult playerResult = new PlayerResult(player, Outcome.LOSE);

        assertThat(playerResult.getReturnMoney()).isEqualTo(new BettingMoney(0));
        assertThat(playerResult.getOutcome()).isEqualTo(Outcome.LOSE);
    }

}