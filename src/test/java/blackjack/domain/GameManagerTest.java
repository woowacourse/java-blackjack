package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameManagerTest {
    @DisplayName("게임 매니저는 덱을 지닌 딜러를 반환한다")
    @Test
    void testDealerCreation() {
        GameManager gameManager = new GameManager();

        Dealer dealer = gameManager.inviteDealer();

        assertThat(dealer).isNotNull();
    }

    @DisplayName("게임 매니저는 명단을 받아 각자 덱을 지닌 플레이어들을 반환한다")
    @Test
    void testPlayersCreation() {
        GameManager gameManager = new GameManager();
        Dealer dealer = gameManager.inviteDealer();

        Players players = gameManager.invitePlayers(List.of("p1, p2"), dealer);

        assertThat(players).isNotNull();
    }

    @DisplayName("게임 매니저는 딜러의 각 플레이어에 대한 승패 결과를 반환한다")
    @Test
    void testDealerFinalResult() {
        GameManager gameManager = new GameManager();
        Dealer dealer = gameManager.inviteDealer();
        Players players = gameManager.invitePlayers(List.of("p1, p2"), dealer);

        Map<GameResult, Integer> dealerFinalResult = gameManager.generateDealerFinalResult(dealer, players);

        assertThat(dealerFinalResult).isNotNull();
    }

    @DisplayName("게임 매니저는 플레이어들의 승패 결과를 반환한다")
    @Test
    void testPlayersFinalResult() {
        GameManager gameManager = new GameManager();
        Dealer dealer = gameManager.inviteDealer();
        Players players = gameManager.invitePlayers(List.of("p1, p2"), dealer);

        Map<Player, GameResult> playersFinalResult = gameManager.generatePlayersFinalResult(dealer, players);

        assertThat(playersFinalResult).isNotNull();
    }
}
