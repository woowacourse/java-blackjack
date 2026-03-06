package service;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackJackServiceTest {

    @Test
    @DisplayName("딜러와 모든 플레이어는 게임 시작 시, 2장의 카드를 받는다.")
    void 게임_시작시_2장_카드() {
        Dealer dealer = new Dealer();
        Players players = new Players(List.of("pobi", "james"));
        BlackJackService blackJackService = new BlackJackService(new Deck(), dealer, players);

        Assertions.assertEquals(dealer.getHand().getHand().size(), 0);
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(player.getHand().getHand().size(), 0);
        }

        blackJackService.initHand();

        Assertions.assertEquals(dealer.getHand().getHand().size(), 2);
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(player.getHand().getHand().size(), 2);
        }
    }
}
