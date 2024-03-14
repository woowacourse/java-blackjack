package blackjack.domain.rule;

import static blackjack.domain.rule.GameResult.BLACKJACK_WIN;
import static blackjack.domain.rule.GameResult.PLAYER_LOSE;
import static blackjack.domain.rule.GameResult.PLAYER_WIN;
import static blackjack.domain.rule.GameResult.PUSH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.DealerGameResult;
import blackjack.domain.card.TestHandCreator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.player.TestPlayerCreator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    private Judge judge;

    @BeforeEach
    void setUp() {
        judge = new Judge();
    }

    @DisplayName("딜러와 플레이어 중 누가 이겼는지 알 수 있다")
    @Test
    void testSelectWinner() {
        Player player = TestPlayerCreator.of("리비", 2, 3);
        Dealer dealer = new Dealer(TestHandCreator.of(3, 4));
        assertThat(judge.isPlayerWin(dealer, player)).isFalse();
    }

    @DisplayName("플레이어 블랙잭이고 딜러가 블랙잭이 아니면 플레이어가 블랙잭 승리를 한다.")
    @Test
    void testPlayerBlackJackWin() {
        Player player = TestPlayerCreator.of("리비", 1, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(3, 4));
        assertThat(judge.calculatePlayerResult(dealer, player)).isEqualTo(BLACKJACK_WIN);
    }

    @DisplayName("딜러와 플레이어 모두 21점이더라도 블랙잭 핸드가 있다면 블랙잭쪽이 이긴다.")
    @Test
    void testPlayerBlackJackWinMaxScore() {
        Player player = TestPlayerCreator.of("리비", 1, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(9, 2, 10));
        assertThat(judge.calculatePlayerResult(dealer, player)).isEqualTo(BLACKJACK_WIN);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭인 경우 결과는 PUSH이다.")
    @Test
    void testPlayerAndDealerBlackJack() {
        Player player = TestPlayerCreator.of("리비", 1, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(1, 10));
        assertThat(judge.calculatePlayerResult(dealer, player)).isEqualTo(PUSH);
    }

    @DisplayName("딜러가 버스트 되고 플레이어가 살아있다면 플레이어가 승리한다")
    @Test
    void testPlayerWin() {
        Player player = TestPlayerCreator.of("리비", 10, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(2, 10));
        assertThat(judge.calculatePlayerResult(dealer, player)).isEqualTo(PLAYER_WIN);
    }

    @DisplayName("딜러 플레이어 모두 버스트 되지 않은 경우 딜러의 점수보다 플레이어의 점수가 낮다면 플레이어가 패한다")
    @Test
    void testPlayerLose() {
        Player player = TestPlayerCreator.of("리비", 2, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(10, 10));
        assertThat(judge.calculatePlayerResult(dealer, player)).isEqualTo(PLAYER_LOSE);
    }
    
    @DisplayName("딜러의 전적을 계산할 수 있다")
    @Test
    void testCalculateDealerResult() {
        Player player = TestPlayerCreator.of("리비", 2, 3);
        Dealer dealer = new Dealer(TestHandCreator.of(3, 4));
        Players players = new Players(List.of(player));
        DealerGameResult dealerGameResult = judge.calculateDealerGameResult(dealer, players);
        assertAll(
                () -> assertThat(dealerGameResult.getWinCount()).isEqualTo(1),
                () -> assertThat(dealerGameResult.getLoseCount()).isEqualTo(0)
        );
    }
}
