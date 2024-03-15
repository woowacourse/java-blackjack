package domain.user;

import static domain.card.Number.ACE;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.FOUR;
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
import static view.Command.NO;
import static view.Command.YES;

import domain.Deck;
import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("커맨드가 YES 이면 덱에서 카드를 한장 받는다.")
    void receiveCardTest() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, TEN), new Card(SPADE, EIGHT)));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> YES, deck);

        assertThat(player.getAllCards()).contains(card);
    }

    @Test
    @DisplayName("커맨드가 NO이면 카드를 받지 않는다.")
    void notReceiveCardTest() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, TEN), new Card(SPADE, EIGHT)));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> NO, deck);

        assertThat(player.getAllCards()).doesNotContain(card);
    }

    @Test
    @DisplayName("블랙잭이면 커맨드가 YES 여도 받지 않는다.")
    void receiveCardWhenBlackJackTest() {
        Player player = new Player(new Name("aa"), new Hand(new Card(SPADE, TEN), new Card(SPADE, ACE)));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> YES, deck);

        assertThat(player.getAllCards()).doesNotContain(card);
    }

    @Test
    @DisplayName("카드의 합이 21 이상이면 카드를 더 받지 않는다.")
    void receiveCardWhenSumOver21() {
        Player player = new Player(new Name("aa"),
                new Hand(new Card(SPADE, TEN), new Card(SPADE, FOUR), new Card(SPADE, SEVEN)));
        Card card = new Card(SPADE, FIVE);
        Deck deck = new Deck(List.of(card));

        player.receiveCard(name -> YES, deck);

        assertThat(player.getAllCards()).doesNotContain(card);
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
