package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BettingResultTest {

    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("벡터", 10000);
        player2 = new Player("한스", 5000);
    }

    @Test
    void 참가자가_승_이면_배팅금액만큼_얻고_패면_배팅금액을_잃는다() {
        // given
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.WIN);
        winLoseResult.put(player2, PlayerResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(10000);
        assertThat(bettingResult.getBettingResult().get(player2)).isEqualTo(-5000);
    }

    @Test
    void 참가자가_블랙잭이면_1_5배_얻는다() {
        // given
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.BLACKJACK);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(15000);
    }

    @Test
    void 참가자가_무승부면_0원을_얻는다() {
        // given
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.DRAW);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(0);
    }

    @Test
    void 딜러의_손익을_계산한다() {
        // given
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.WIN);
        winLoseResult.put(player2, PlayerResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        // when
        int dealerResult = bettingResult.getDealerResult();

        // then
        assertThat(dealerResult).isEqualTo(-5000);
    }

    @Test
    void 여러명의_참가자에_대한_배팅_결과를_검증한다() {
        // given
        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.WIN);
        winLoseResult.put(player2, PlayerResult.BLACKJACK);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(10000);
        assertThat(bettingResult.getBettingResult().get(player2)).isEqualTo(7500);
    }
}
