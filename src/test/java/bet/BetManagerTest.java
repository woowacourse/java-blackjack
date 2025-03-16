package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import player.Player;
import result.MatchResult;

class BetManagerTest {

    @Test
    void 참여자의_베팅_금액을_추가한다() {
        // given
        final int amount = 10000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();

        // when & then
        Assertions.assertThatNoException()
                .isThrownBy(() -> betManager.addBet(player, amount));
    }

    @Test
    void 참여자가_존재하지_않는데_베팅한다면_예외가_발생한다() {
        // given
        final int amount = 10000;
        Player player = new Participant("시소");
        Player noSuchPlayer = new Participant("omg");

        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        // when & then
        Assertions.assertThatThrownBy(() -> betManager.getBet(noSuchPlayer))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 참여자의_베팅_금액을_반환한다() {
        // given
        final int amount = 10000;
        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        // when & then
        Assertions.assertThat(betManager.getBet(player).getAmount())
                .isEqualTo(amount);
    }

    @Test
    void 딜러의_최종_수익_금액을_계산한다() {
        // given
        final int amount = 10000;
        final int expected = -10000;

        Player player = new Participant("시소");
        BetManager betManager = new BetManager();
        betManager.addBet(player, amount);

        // when & then
        Assertions.assertThat(betManager.calculateDealerBettingResult())
                .isEqualTo(expected);
    }

    @Test
    void 참여자들의_최종_수익_금액을_계산한다() {
        // given
        final int amount = 10000;
        Player player1 = new Participant("시시");
        Player player2 = new Participant("소소");

        BetManager betManager = new BetManager();
        betManager.addBet(player1, amount);
        betManager.addBet(player2, amount);

        Map<Player, MatchResult> matchResults = new LinkedHashMap<>();
        matchResults.put(player1, MatchResult.WIN);
        matchResults.put(player2, MatchResult.DRAW);

        // when
        betManager.calculateBettingResult(matchResults);

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(betManager.getBet(player1).getAmount()).isEqualTo(amount);
            softly.assertThat(betManager.getBet(player2).getAmount()).isEqualTo(0);
        });
    }
}