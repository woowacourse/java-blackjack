package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import blackjack.domain.card.TestDeckFactory;
import blackjack.domain.gamer.BetAmount;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    @DisplayName("플레이어의 최종 수익을 반환한다.")
    @Test
    void returnPlayersProfit() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.NINE, Suit.SPADE));
        cards.push(new Card(Number.TEN, Suit.SPADE));
        cards.push(new Card(Number.TWO, Suit.HEART));
        cards.push(new Card(Number.THREE, Suit.HEART));
        cards.push(new Card(Number.NINE, Suit.DIAMOND));
        cards.push(new Card(Number.EIGHT, Suit.DIAMOND));
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.EIGHT, Suit.SPADE));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Players players = new Players(List.of(
                Player.of("pobi", 10000),
                Player.of("jason", 20000),
                Player.of("nyangin", 30000))
        );
        final Dealer dealer = new Dealer();
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        Map<Name, BetAmount> playersProfit = gameBoard.getPlayersProfit();

        // then
        assertAll(
                () -> assertThat(playersProfit.get(new Name("pobi"))).isEqualTo(new BetAmount(0)),
                () -> assertThat(playersProfit.get(new Name("jason"))).isEqualTo(new BetAmount(-20000)),
                () -> assertThat(playersProfit.get(new Name("nyangin"))).isEqualTo(new BetAmount(30000))
        );
    }

    @DisplayName("딜러의 최종 수익을 반환한다.")
    @Test
    void returnDealerProfits() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.NINE, Suit.SPADE));
        cards.push(new Card(Number.TEN, Suit.SPADE));
        cards.push(new Card(Number.TWO, Suit.HEART));
        cards.push(new Card(Number.THREE, Suit.HEART));
        cards.push(new Card(Number.NINE, Suit.DIAMOND));
        cards.push(new Card(Number.EIGHT, Suit.DIAMOND));
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.EIGHT, Suit.SPADE));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Players players = new Players(List.of(
                Player.of("pobi", 10000),
                Player.of("jason", 20000),
                Player.of("nyangin", 30000))
        );
        final Dealer dealer = new Dealer();
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        double dealerProfit = gameBoard.getDealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo(-10000);
    }
}
