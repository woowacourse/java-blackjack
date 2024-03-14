package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import blackjack.domain.card.TestDeckFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Stack;

class GameResultTest {

    @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이면 플레이어의 게임 결과는 블랙잭이다.")
    @Test
    void returnBlackJackWhenDealerIsNotBlackJackAndPlayerIsBlackJack() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.HEART));
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.EIGHT, Suit.HEART));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.BLACKJACK);
    }

    @DisplayName("딜러가 버스트이면 플레이어의 게임 결과는 승리이다.")
    @Test
    void returnWinWhenDealerIsBust() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.HEART));
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.ACE, Suit.DIAMOND));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));

        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어가 버스트가 아니고 플레이어의 점수가 딜러의 점수보다 크면 플레이어의 게임 결과는 승리이다.")
    @Test
    void returnWinWhenPlayerIsNotBustAndPlayerScoreIsGreaterThanDealerScore() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.HEART));
        cards.push(new Card(Number.EIGHT, Suit.HEART));
        cards.push(new Card(Number.SEVEN, Suit.HEART));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어가 버스트이면 플레이어의 게임 결과는 패배이다.")
    @Test
    void returnLoseWhenPlayerIsBust() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.ACE, Suit.DIAMOND));
        cards.push(new Card(Number.EIGHT, Suit.HEART));
        cards.push(new Card(Number.SEVEN, Suit.HEART));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 블랙잭이고 플레이어가 블랙잭이 아니면 플레이어의 게임 결과는 패배이다.")
    @Test
    void returnLoseWhenDealerIsBlackJackAndPlayerIsNotBlackJack() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.EIGHT, Suit.HEART));
        cards.push(new Card(Number.NINE, Suit.DIAMOND));
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.HEART));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어의 점수가 딜러의 점수보다 낮으면 플레이어의 게임 결과는 패배이다.")
    @Test
    void returnLoseWhenPlayerScoreIsLessThanDealerScore() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.TEN, Suit.HEART));
        cards.push(new Card(Number.EIGHT, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.DIAMOND));
        cards.push(new Card(Number.NINE, Suit.DIAMOND));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러와 플레이어 모두 버스트이면 플레이어의 게임 결과는 무승부이다.")
    @Test
    void returnPushWhenDealerAndPlayerAreBust() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.ACE, Suit.DIAMOND));
        cards.push(new Card(Number.ACE, Suit.CLUB));
        cards.push(new Card(Number.ACE, Suit.SPADE));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭이면 플레이어의 게임 결과는 무승부이다.")
    @Test
    void returnPushWhenDealerAndPlayerAreBlackJack() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.DIAMOND));
        cards.push(new Card(Number.ACE, Suit.CLUB));
        cards.push(new Card(Number.TEN, Suit.SPADE));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("플레이어가 버스트가 아니고 플레이어의 점수와 딜러의 점수가 같으면 플레이어의 게임 결과는 무승부이다.")
    @Test
    void returnPushWhenPlayerIsNotBustAndPlayerScoreAndDealerScoreIsSame() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.DIAMOND));
        cards.push(new Card(Number.NINE, Suit.CLUB));
        cards.push(new Card(Number.TEN, Suit.SPADE));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)
        ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        // then
        assertThat(gameResult).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("플레이어의 수익을 반환한다.")
    @Test
    void returnPlayerProfit() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.FOUR, Suit.HEART));
        cards.push(new Card(Number.THREE, Suit.SPADE));
        cards.push(new Card(Number.TWO, Suit.HEART));
        cards.push(new Card(Number.THREE, Suit.HEART));
        cards.push(new Card(Number.NINE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.SPADE));
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.HEART));
        cards.push(new Card(Number.FOUR, Suit.SPADE));
        cards.push(new Card(Number.THREE, Suit.DIAMOND));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000),
                Player.of("jason", 20000),
                Player.of("nyang", 30000),
                Player.of("in", 40000)
                ));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final double playerProfit = GameResult.calculatePlayerProfit(dealer, players.getPlayers().get(0));
        final double playerProfit2 = GameResult.calculatePlayerProfit(dealer, players.getPlayers().get(1));
        final double playerProfit3 = GameResult.calculatePlayerProfit(dealer, players.getPlayers().get(2));
        final double playerProfit4 = GameResult.calculatePlayerProfit(dealer, players.getPlayers().get(3));

        // then
        assertAll(
                () -> assertThat(playerProfit).isEqualTo(15000),
                () -> assertThat(playerProfit2).isEqualTo(20000),
                () -> assertThat(playerProfit3).isEqualTo(-30000),
                () -> assertThat(playerProfit4).isEqualTo(0)
        );
    }
    
    @DisplayName("딜러의 수익을 반환한다.")
    @Test
    void returnDealerProfit() {
        // given
        final Stack<Card> cards = new Stack<>();
        cards.push(new Card(Number.ACE, Suit.HEART));
        cards.push(new Card(Number.TEN, Suit.DIAMOND));
        cards.push(new Card(Number.TWO, Suit.CLUB));
        cards.push(new Card(Number.THREE, Suit.SPADE));
        final Deck deck = new Deck(new TestDeckFactory(cards));

        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)));
        final Gamers gamers = new Gamers(dealer, players);

        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        // when
        final double dealerProfit = GameResult.calculateDealerProfit(dealer, players.getPlayers().get(0));

        // then
        assertThat(dealerProfit).isEqualTo(-15000);
    }
}
