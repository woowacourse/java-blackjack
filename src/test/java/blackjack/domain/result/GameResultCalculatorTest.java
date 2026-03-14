package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultCalculatorTest {

    private final GameResultCalculator calculator = new GameResultCalculator();

    @Test
    void 플레이어가_버스트이면_패이다() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.CLUB, Rank.EIGHT));
        player.receiveCard(new Card(Suit.CLUB, Rank.NINE));
        player.receiveCard(new Card(Suit.CLUB, Rank.FIVE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러가_버스트이면_승이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLUB, Rank.EIGHT));
        dealer.receiveCard(new Card(Suit.CLUB, Rank.NINE));
        dealer.receiveCard(new Card(Suit.CLUB, Rank.FIVE));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어_점수가_딜러보다_높으면_승이다() {
        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.CLUB, Rank.EIGHT));
        player.receiveCard(new Card(Suit.CLUB, Rank.JACK));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어_점수가_딜러보다_낮으면_패이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLUB, Rank.EIGHT));
        dealer.receiveCard(new Card(Suit.CLUB, Rank.JACK));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어_점수와_딜러_점수가_같으면_무승부이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLUB, Rank.SEVEN));
        dealer.receiveCard(new Card(Suit.CLUB, Rank.JACK));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이면_무승부이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLUB, Rank.ACE));
        dealer.receiveCard(new Card(Suit.CLUB, Rank.JACK));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.ACE));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어만_블랙잭이면_게임_결과는_블랙잭이다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.CLUB, Rank.EIGHT));
        dealer.receiveCard(new Card(Suit.CLUB, Rank.JACK));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.ACE));

        assertThat(calculator.calculate(player, dealer)).isEqualTo(GameResult.BLACKJACK);
    }
}
