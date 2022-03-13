package blackJack.domain.result;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WinDrawLoseTest {
    private final Player player = new Player("k");
    private final Dealer dealer = new Dealer();

    @Test
    @DisplayName("둘 다 21점 미만이면서 플레이어가 딜러보다 점수가 높은 경우")
    void playerWinByScore() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("둘 다 21점 미만이면서 플레이어가 딜러와 점수가 같은 경우")
    void playerDrawByScore() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.DIAMOND, Denomination.JACK));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.DRAW);
    }

    @Test
    @DisplayName("둘 다 21점 미만이면서 플레이어가 딜러보다 점수가 낮은 경우")
    void playerLoseByScore() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("플레이어만 버스트된 경우")
    void playerBust() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Symbol.DIAMOND, Denomination.JACK));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.TWO));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트된 경우")
    void dealerBust() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.DIAMOND, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.TWO));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트된 경우")
    void allBust() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Symbol.DIAMOND, Denomination.JACK));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.TWO));
        dealer.receiveCard(new Card(Symbol.HEART, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.TWO));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.LOSE);
    }

    @Test
    @DisplayName("둘 다 21점이지만, 플레이어가 블랙잭인 경우")
    void playerWinByBlackJack() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.NINE));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.TWO));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.WIN);
    }

    @Test
    @DisplayName("둘 다 21점이고, 둘 다 블랙잭인 경우")
    void playerDrawByBlackJack() {
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        dealer.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));

        assertThat(WinDrawLose.calculateWinDrawLose(player, dealer)).isEqualTo(WinDrawLose.DRAW);
    }
}
