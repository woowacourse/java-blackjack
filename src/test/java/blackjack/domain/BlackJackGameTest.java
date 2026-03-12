package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {
    @Test
    void 게임_시작시_플레이어와_딜러는_각각_두장의_카드를_받는다() {
        Players players = new Players(List.of("pobi", "jason"));
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJackGame game = new BlackJackGame(players, dealer, deck);

        game.initDraw();

        assertThat(players.getPlayers().get(0).getCardCount()).isEqualTo(2);
        assertThat(players.getPlayers().get(1).getCardCount()).isEqualTo(2);
        assertThat(dealer.getCardCount()).isEqualTo(2);
    }

}
