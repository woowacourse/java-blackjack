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

        Deck deck = new Deck(List.of(
                new Card(CardPoint.TWO, CardPattern.CLUB),
                new Card(CardPoint.THREE, CardPattern.DIAMOND),
                new Card(CardPoint.NINE, CardPattern.HEART),
                new Card(CardPoint.KING, CardPattern.SPADE),
                new Card(CardPoint.FIVE, CardPattern.CLUB),
                new Card(CardPoint.ACE, CardPattern.HEART)
        ));

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);

        blackJackGame.initDraw();

        assertThat(player.isBlackJack()).isTrue();
    }

}
