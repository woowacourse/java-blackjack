package bet;

import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import player.Participant;
import player.Player;

class BetManagerTest {

    @Test
    void 참여자의_베팅_금액을_추가한다() {
        final int amount = 10000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();

        Assertions.assertThatNoException()
                .isThrownBy(() -> betManager.addBet(player, amount));
    }

    @Test
    void 참여자의_베팅_금액을_반환한다() {
        final int amount = 10000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        Assertions.assertThat(betManager.getBetAmount(player))
                .isEqualTo(amount);
    }

    @Test
    void 승리한_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 20000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        betManager.calculateBettingResult(false, true, false);

        Assertions.assertThat(betManager.getBetAmount(player))
                .isEqualTo(expected);
    }

    @Test
    void 블랙잭인_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 25000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        betManager.calculateBettingResult(true, true, false);

        Assertions.assertThat(betManager.getBetAmount(player))
                .isEqualTo(expected);
    }

    @Test
    void 무승부인_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 10000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        betManager.calculateBettingResult(false, false, true);

        Assertions.assertThat(betManager.getBetAmount(player))
                .isEqualTo(expected);
    }

    @Test
    void 패배한_플레이어의_베팅_결과를_계산한다() {
        final int amount = 10000;
        final int expected = 0;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        betManager.calculateBettingResult(false, false, false);

        Assertions.assertThat(betManager.getBetAmount(player))
                .isEqualTo(expected);
    }
}