package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private static final BetAmount DEFAULT_BET_AMOUNT = new BetAmount(1000);

    private Player createPlayer(String name, int amount) {
        return new Player(new Name(name), new BetAmount(amount));
    }

    @Test
    void 게임_시작시_플레이어와_딜러는_각각_두장의_카드를_받는다() {
        Players players = new Players(List.of(
                new Player(new Name("pobi"), DEFAULT_BET_AMOUNT),
                new Player(new Name("jason"), DEFAULT_BET_AMOUNT)
        ));
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJackGame game = new BlackJackGame(players, dealer, deck);

        game.initDraw();

        assertThat(players.getPlayers().get(0).getCardCount()).isEqualTo(2);
        assertThat(players.getPlayers().get(1).getCardCount()).isEqualTo(2);
        assertThat(dealer.getCardCount()).isEqualTo(2);
    }


    @Test
    void 시작_카드가_A와_K이면_플레이어는_블랙잭이다() {
        Player player = new Player(new Name("pobi"), DEFAULT_BET_AMOUNT);
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer();

        Deck deck = new Deck(new FixedOrderShuffleStrategy(List.of(
                new Card(CardPoint.TWO, CardPattern.CLUB),
                new Card(CardPoint.THREE, CardPattern.DIAMOND),
                new Card(CardPoint.NINE, CardPattern.HEART),   // dealer 2라운드 - 4번째 뽑힘
                new Card(CardPoint.KING, CardPattern.SPADE),   // pobi 2라운드 - 3번째 뽑힘
                new Card(CardPoint.FIVE, CardPattern.CLUB),    // dealer 1라운드  2번째 뽑힘
                new Card(CardPoint.ACE, CardPattern.HEART)     // pobi 1라운드  - 1번째 뽑힘
        )));

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.initDraw();

        assertThat(player.isBlackJack()).isTrue();
    }


    @Test
    void NoShuffleStrategy를_주입하면_카드_순서가_유지된다() {
        Deck deck = new Deck(new NoShuffleStrategy());

        // draw()는 removeLast() 이므로 마지막 카드(CLUB × ACE)가 첫 번째로 나옴
        Card firstDrawn = deck.draw();
        assertThat(firstDrawn.getName()).isEqualTo("A클로버");
    }
}
