package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BettingResultTest {

    private Dealer dealer;
    private Player player1;
    private Player player2;
    private Players players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player1 = new Player("벡터");
        player2 = new Player("한스");
        players = new Players(List.of(player1, player2));
        player1.setBetting(10000);
        player2.setBetting(1000);
    }

    @Test
    void 참가자가_승_이면_배팅금액만큼_얻고_패면_배팅금액을_잃는다() {
        // given

        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.WIN);
        winLoseResult.put(player2, PlayerResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        int participant1Result = bettingResult.getBettingResult().get(player1);
        int participant2Result = bettingResult.getBettingResult().get(player2);
        // then
        assertThat(participant1Result).isEqualTo(10000);
        assertThat(participant2Result).isEqualTo(-1000);
    }

    @Test
    void 참가자가_블랙잭이면_1_5배_얻는다() {
        // given

        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.BLACKJACK);
        winLoseResult.put(player2, PlayerResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        int participant1Result = bettingResult.getBettingResult().get(player1);
        int participant2Result = bettingResult.getBettingResult().get(player2);
        // then
        assertThat(participant1Result).isEqualTo(15000);
    }

    @Test
    void 참가자가_무승부면_0원을_얻는다() {
        // given

        Map<Player, PlayerResult> winLoseResult = new HashMap<>();
        winLoseResult.put(player1, PlayerResult.DRAW);
        winLoseResult.put(player2, PlayerResult.LOSE);

        BettingResult bettingResult = new BettingResult(winLoseResult);

        int participant1Result = bettingResult.getBettingResult().get(player1);
        int participant2Result = bettingResult.getBettingResult().get(player2);
        // then
        assertThat(participant1Result).isEqualTo(0);
    }

}
