package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("게임 시작 시 딜러와 모든 플레이어는 처음 카드 2장을 받는다")
    void 게임_시작시_딜러와_플레이어에게_카드2장_배분() {
        List<PlayerInfo> playerInfos = List.of(
                new PlayerInfo(Name.from("pobi"), new Betting(10000)),
                new PlayerInfo(Name.from("jason"), new Betting(20000)));

        BlackjackGame blackjackGame = BlackjackGame.start(playerInfos);

        Dealer dealer = blackjackGame.getDealer();
        Players players = blackjackGame.getPlayers();

        assertThat(dealer.getHand().getCards()).hasSize(2);
        assertThat(players.getPlayers()).hasSize(2);

        for (Player player : players.getPlayers()) {
            assertThat(player.getHand().getCards()).hasSize(2);
        }
    }

    @Test
    @DisplayName("모든 플레이어가 버스트면 딜러는 카드를 더 뽑지 않는다")
    void 모든_플레이어_버스트면_딜러는_카드뽑는걸_중단한다() {
        List<PlayerInfo> playerInfos = List.of(
                new PlayerInfo(Name.from("pobi"), new Betting(10000)),
                new PlayerInfo(Name.from("jason"), new Betting(20000)));

        BlackjackGame blackjackGame = BlackjackGame.start(playerInfos);
        for (Player player : blackjackGame.getPlayers().getPlayers()) {
            makePlayerBust(blackjackGame, player);
        }

        boolean result = blackjackGame.shouldDealerDraw();
        assertThat(result).isFalse();
    }

    private void makePlayerBust(BlackjackGame blackjackGame, Player player) {
        while (!player.isBust()) {
            blackjackGame.addPlayerCard(player);
        }
    }
}