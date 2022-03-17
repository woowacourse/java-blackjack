package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

class ResultTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        dealer.drawCard(new Card(Denomination.TWO, Suit.DIAMONDS));
        dealer.drawCard(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        player = new Player("a");
    }

    @Test
    @DisplayName("딜러가 이긴 경우")
    void dealerWinTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        player.drawCard(new Card(Denomination.THREE, Suit.DIAMONDS));

        Map<Gamer, Result> map = Result.judge(dealer, player);

        assertThat(map.get(dealer).getValue()).isEqualTo("승");
    }

    @Test
    @DisplayName("플레이어가 이긴 경우")
    void playerWinTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.DIAMONDS));
        player.drawCard(new Card(Denomination.TEN, Suit.DIAMONDS));

        Map<Gamer, Result> map = Result.judge(dealer, player);

        assertThat(map.get(player).getValue()).isEqualTo("승");
    }

    @Test
    @DisplayName("비긴 경우")
    void drawTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        player.drawCard(new Card(Denomination.EIGHT, Suit.CLUBS));

        Map<Gamer, Result> map = Result.judge(dealer, player);

        assertThat(map.get(dealer).getValue()).isEqualTo("무");
    }
}