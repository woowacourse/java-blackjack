package blackjack.domain;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player(new Name("pobi"));
        dealer.receiveFirstHand(Arrays.asList(
                new Card(Pattern.HEART, Number.TEN),
                new Card(Pattern.HEART, Number.JACK)
        ));
        player.receiveFirstHand(Arrays.asList(
                new Card(Pattern.CLOVER, Number.TEN),
                new Card(Pattern.CLOVER, Number.JACK)
        ));
    }

    @Test
    @DisplayName("딜러와 플레이어 둘다 버스트가 아닐 때 플레이어의 점수가 더 높으면 플레이어의 승리")
    void playerScoreBiggerThanDealerScoreTest() {
        player.receiveCard(new Card(Pattern.CLOVER, Number.ACE));
        assertThat(dealer.judgeHand(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러가 버스트, 플레이어는 스테이라면 플레이어 승리")
    void ifDealerBustedAndPlayerStayTest() {
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.TWO));
        player.receiveCard(new Card(Pattern.CLOVER, Number.ACE));
        assertThat(dealer.judgeHand(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어 둘다 버스트가 아닐 때 딜러의 점수가 더 높으면 플레이어의 패배")
    void dealerScoreBiggerThanPlayerScoreTest() {
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.ACE));
        assertThat(dealer.judgeHand(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트고 딜러는 스테이라면 플레이어의 패배")
    void ifPlayerBustedAndDealerStayTest() {
        player.receiveCard(new Card(Pattern.HEART, Number.TWO));
        assertThat(dealer.judgeHand(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 둘다 버스트라면 플레이어의 패배")
    void ifPlayerAndDealerBothBustedTest() {
        dealer.receiveCard(new Card(Pattern.CLOVER, Number.TWO));
        player.receiveCard(new Card(Pattern.HEART, Number.TWO));
        assertThat(dealer.judgeHand(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 버스트가 아니고 점수가 같으면 무승부")
    void tieTest() {
        assertThat(dealer.judgeHand(player)).isEqualTo(GameResult.TIE);
    }

    @Test
    @DisplayName("플레이어의 승패에 따라 딜러 승패가 잘 결정되는지 테스트")
    void testDealerWinOrLoseByPlayer() {
        player.receiveCard(new Card(Pattern.DIAMOND, Number.ACE));
        GameResult playerWin = dealer.judgeHand(player);
        player.receiveCard(new Card(Pattern.DIAMOND, Number.ACE));
        GameResult playerLose = dealer.judgeHand(player);

        assertThat(GameResult.reverseResult(playerWin)).isEqualTo(GameResult.LOSE);
        assertThat(GameResult.reverseResult(playerLose)).isEqualTo(GameResult.WIN);
    }
}
