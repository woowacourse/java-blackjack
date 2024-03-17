package domain.user;

import static domain.card.Number.ACE;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLUB;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static domain.money.GameResult.DRAW;
import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("손패의 합이 21 미만이면 receivable이다.")
    void isReceivableTest() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, JACK), new Card(SPADE, QUEEN)));

        assertThat(player.isReceivable()).isTrue();
    }

    @Test
    @DisplayName("손패의 합이 21 이상이면 receivable이지 않다.")
    void isNotReceivableTest() {
        Player player = new Player(new Name("aa"),
                new Hand(new Card(SPADE, JACK), new Card(SPADE, QUEEN), new Card(SPADE, ACE)));

        assertThat(player.isReceivable()).isFalse();
    }

    @Test
    @DisplayName("player(15), dealer(11) -> WIN")
    void should_win_when_player15dealer11() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, SEVEN), new Card(SPADE, EIGHT)));
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX)));

        assertThat(player.generateResult(dealer)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("player(11), dealer(11) -> DRAW")
    void should_draw_when_player11dealer11() {
        Player player = new Player(new Name("aa"), new Hand(new Card(HEART, FIVE), new Card(HEART, SIX)));
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX)));

        assertThat(player.generateResult(dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("player(11), dealer(15) -> LOSE")
    void should_lose_when_player11dealer15() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX)));
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, SEVEN), new Card(SPADE, EIGHT)));

        assertThat(player.generateResult(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("player(Blackjack), dealer(21) -> WIN")
    void should_win_when_playerBlackjackDealer21() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, ACE), new Card(SPADE, TEN)));
        Dealer dealer = new Dealer(new Hand(new Card(SPADE, FIVE), new Card(SPADE, SIX), new Card(SPADE, JACK)));

        assertThat(player.generateResult(dealer)).isEqualTo(WIN);
    }

    @Test
    @DisplayName("player(Blackjack), dealer(Blackjack) -> DRAW")
    void should_draw_when_playerBlackjackDealerBlackjack() {
        Player player = new Player(new Name("aa"), new Hand(new Card(HEART, ACE), new Card(HEART, TEN)));
        Dealer dealer = new Dealer(new Hand(new Card(CLUB, ACE), new Card(CLUB, TEN)));

        assertThat(player.generateResult(dealer)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("player(21), dealer(Blackjack) -> LOSE")
    void should_lose_when_player21dealerBlackjack() {
        Player player = new Player(new Name("aa"),
                new Hand(new Card(CLUB, FIVE), new Card(CLUB, SIX), new Card(CLUB, JACK)));
        Dealer dealer = new Dealer(new Hand(new Card(CLUB, ACE), new Card(CLUB, TEN)));

        assertThat(player.generateResult(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("player(Busted), dealer(11) -> LOSE")
    void should_lose_when_playerBustedDealer11() {
        Player player = new Player(new Name("aa"),
                new Hand(new Card(CLUB, QUEEN), new Card(CLUB, TEN), new Card(CLUB, JACK)));
        Dealer dealer = new Dealer(new Hand(new Card(CLUB, FIVE), new Card(CLUB, SIX)));

        assertThat(player.generateResult(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("player(Busted), dealer(Busted) -> LOSE")
    void should_lose_when_playerBustedDealerBusted() {
        Player player = new Player(new Name("aa"),
                new Hand(new Card(CLUB, QUEEN), new Card(CLUB, TEN), new Card(CLUB, JACK)));
        Dealer dealer = new Dealer(new Hand(new Card(HEART, QUEEN), new Card(HEART, TEN), new Card(HEART, JACK)));

        assertThat(player.generateResult(dealer)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("player(11), dealer(Busted) -> WIN")
    void should_win_when_player11dealerBusted() {
        Player player = new Player(new Name("aa"), new Hand(new Card(CLUB, FIVE), new Card(CLUB, SIX)));
        Dealer dealer = new Dealer(new Hand(new Card(CLUB, QUEEN), new Card(CLUB, TEN), new Card(CLUB, JACK)));

        assertThat(player.generateResult(dealer)).isEqualTo(WIN);
    }
}
