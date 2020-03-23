package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultMatcherTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        player = new Player("pobi", "10000");
    }

    @Test
    @DisplayName("player가 버스트 되면 무조건 패배 (dealer 버스트 x)")
    void playerBusted() {
        makeGamerBusted(player);
        makeGamerNotBusted(dealer);
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.LOSE);
    }

    @Test
    @DisplayName("player가 버스트 되면 무조건 패배 (dealer 버스트 o)")
    void playerBusted2() {
        makeGamerBusted(player);
        makeGamerBusted(dealer);
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.LOSE);
    }

    @Test
    @DisplayName("player 버스트 x, dealer 버스트 o")
    void dealerBusted() {
        makeGamerBusted(dealer);
        makeGamerNotBusted(player);
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.WIN);
    }

    @Test
    @DisplayName("player 버스트 x, dealer 버스트  x, 플레이어 승")
    void playerWin() {
        dealer.draw(new Card(CardSymbol.KING, CardType.CLOVER));
        player.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.WIN);
    }

    @Test
    @DisplayName("player 버스트 x, dealer 버스트  x, 플레이어 무")
    void playerDraw() {
        dealer.draw(new Card(CardSymbol.KING, CardType.CLOVER));
        player.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.DRAW);
    }

    @Test
    @DisplayName("player 버스트 x, dealer 버스트  x, 플레이어 패")
    void playerLose() {
        dealer.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        player.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.LOSE);
    }

    @Test
    @DisplayName("player 블랙잭")
    void playerBlackJack() {
        makeGamerBlackJack(player);
        makeGamerNotBusted(dealer);
        assertThat(BlackJackResult.findResult(dealer, player)).isEqualTo(BlackJackResult.BLACKJACK_WIN);
    }

    private void makeGamerBusted(Gamer gamer) {
        gamer.draw(new Card(CardSymbol.KING, CardType.SPADE));
        gamer.draw(new Card(CardSymbol.KING, CardType.CLOVER));
        gamer.draw(new Card(CardSymbol.KING, CardType.DIAMOND));
    }

    private void makeGamerNotBusted(Gamer gamer) {
        gamer.draw(new Card(CardSymbol.FIVE, CardType.DIAMOND));
        gamer.draw(new Card(CardSymbol.THREE, CardType.SPADE));
    }

    private void makeGamerBlackJack(Gamer gamer) {
        gamer.draw(new Card(CardSymbol.ACE, CardType.CLOVER));
        gamer.draw(new Card(CardSymbol.QUEEN, CardType.DIAMOND));
    }

}