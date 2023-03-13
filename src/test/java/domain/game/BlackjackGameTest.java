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
        Player maco = new Player("maco", 10000);
        Player teo = new Player("teo", 20000);

        NumberGenerator numberGenerator = threshold -> 0;
        Deck deck = new Deck(numberGenerator);

        blackjackGame = new BlackjackGame(dealer, List.of(maco,teo), deck);
    }

    @DisplayName("모든 참여자가 hit하면 딜러는 카드를 1장 가지고 있다.")
    @Test
    void hitAll_dealer() {
        blackjackGame.hitAll();

        Dealer dealer = blackjackGame.getDealer();
        assertThat(dealer.getState().handSize()).isEqualTo(1);
    }

    @DisplayName("모든 참여자가 hit하면 각 플레이어는 카드를 1장 가지고 있다.")
    @Test
    void hitAll_player() {
        blackjackGame.hitAll();

        List<Player> players = blackjackGame.getPlayers();

        for (Player player : players) {
            assertThat(player.getState().handSize()).isEqualTo(1);
        }
    }

    @DisplayName("maco가 hit하면 maco는 카드를 한장 가지고 있다.")
    @Test
    void hitPlayer() {
        blackjackGame.hitPlayer("maco");

        Player maco = blackjackGame.findPlayer("maco");

        assertThat(maco.getState().handSize()).isEqualTo(1);
    }

    @DisplayName("딜러는 16이하이면 hit할 수 있다")
    @Test
    void hitDealer_true() {
        Dealer dealer = blackjackGame.getDealer();
        dealer.hit(SPADE_EIGHT);
        dealer.hit(CLOVER_EIGHT);

        assertThat(blackjackGame.hitDealer()).isTrue();
    }

    @DisplayName("딜러는 17이상이면 hit할 수 없다")
    @Test
    void hitDealer_false() {
        Dealer dealer = blackjackGame.getDealer();
        dealer.hit(SPADE_EIGHT);
        dealer.hit(SPADE_NINE);

        assertThat(blackjackGame.hitDealer()).isFalse();
    }

    @DisplayName("딜러가 16에서 hit했을 때 21 이하이면 stay 상태이다")
    @Test
    void hitDealer_stay() {
        Dealer dealer = blackjackGame.getDealer();
        dealer.hit(SPADE_EIGHT);
        dealer.hit(CLOVER_EIGHT);
        dealer.hit(SPADE_THREE);

        assertThat(dealer.getState().isStay()).isTrue();
    }

}
