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
    private Map<Player, PlayerResult> winLoseResult;

    @BeforeEach
    void setUp() {
        player1 = new Player("벡터", 10000);
        winLoseResult = new HashMap<>();
    }

    @Test
    void 참가자가_승_이면_배팅금액을_잃는다() {
        // given
        winLoseResult.put(player1, PlayerResult.WIN);

        // when
        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(10000);
    }

    @Test
    void 참가자가_패면_배팅금액을_잃는다() {
        // given
        winLoseResult.put(player1, PlayerResult.LOSE);

        // when
        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(-10000);
    }

    @Test
    void 참가자가_블랙잭이면_1_5배_얻는다() {
        // given
        winLoseResult.put(player1, PlayerResult.BLACKJACK);

        // when
        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(15000);
    }

    @Test
    void 참가자가_무승부면_0원을_얻는다() {
        // given
        winLoseResult.put(player1, PlayerResult.DRAW);

        // when
        BettingResult bettingResult = new BettingResult(winLoseResult);

        // then
        assertThat(bettingResult.getBettingResult().get(player1)).isEqualTo(0);
    }

    @Test
    void 딜러의_손익을_계산한다() {
        // given
        winLoseResult.put(player1, PlayerResult.WIN);
        BettingResult bettingResult = new BettingResult(winLoseResult);

        // when
        int dealerResult = bettingResult.getDealerResult();

        // then
        assertThat(dealerResult).isEqualTo(-10000);
    }
}
