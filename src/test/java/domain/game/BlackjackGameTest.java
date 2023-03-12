package domain.game;

import domain.strategy.NumberGenerator;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackjackGameTest {

    BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        Dealer dealer = new Dealer("딜러");
        Player maco = new Player("maco");
        Player teo = new Player("teo");

        NumberGenerator numberGenerator = threshold -> 0;
        Deck deck = new Deck(numberGenerator);

        blackjackGame = new BlackjackGame(dealer, List.of(maco,teo), deck);
    }

    @DisplayName("모든 참여자가 hit하면 딜러는 카드를 1장 가지고 있다.")
    @Test
    void hitAll_dealer() {
        blackjackGame.hitAll();

        Dealer dealer = blackjackGame.getDealer();
        assertThat(dealer.getState().cards().size()).isEqualTo(1);
    }

    @DisplayName("모든 참여자가 hit하면 각 플레이어는 카드를 1장 가지고 있다.")
    @Test
    void hitAll_player() {
        blackjackGame.hitAll();

        List<Player> players = blackjackGame.getPlayers();

        for (Player player : players) {
            assertThat(player.getState().cards().size()).isEqualTo(1);
        }
    }

    @DisplayName("maco가 hit하면 maco는 카드를 한장 가지고 있다.")
    @Test
    void hitPlayer() {
        blackjackGame.hitPlayer("maco");

        Player maco = blackjackGame.findPlayer("maco");

        assertThat(maco.getState().cards().size()).isEqualTo(1);
    }

    @DisplayName("딜러는 16이하이면 hit할 수 있다")
    @Test
    void hitDealer() {
        Dealer dealer = blackjackGame.getDealer();
        dealer.hit(SPADE_EIGHT);
        dealer.hit(CLOVER_EIGHT);

        assertThat(blackjackGame.hitDealer()).isTrue();
    }

    @DisplayName("딜러는 17이상이면 hit할 수 없다")
    @Test
    void hitDealer2() {
        Dealer dealer = blackjackGame.getDealer();
        dealer.hit(SPADE_EIGHT);
        dealer.hit(SPADE_NINE);
        blackjackGame.hitDealer();

        assertThat(blackjackGame.hitDealer()).isFalse();
    }
}
