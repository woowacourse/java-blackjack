package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {
    @Test
    @DisplayName("모든 플레이어에게 시작 카드를 분배한다.")
    void 모든_플레이어에게_시작_카드들을_분배() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(
                new Player("moda"),
                new Player("daro"),
                new Player("pobi")));
        blackjackGame.dealInitially(players, dealer);

        //when, then
        for (Player player : players.getPlayers()) {
            assertThat(player.getHandCards().size()).isEqualTo(2);
        }
        assertThat(dealer.getHandCards().size()).isEqualTo(2);
    }
}