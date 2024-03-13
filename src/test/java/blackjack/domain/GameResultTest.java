package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.TestDeckFactory;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class GameResultTest {

    /**
     * 카드 순서 :
     * Ace - HEART, SPADE, CLOVER, DIAMOND
     * 2 - HEART, SPADE, CLOVER, DIAMOND
     * ...
     * KING - HEART, SPADE, CLOVER, DIAMOND
     */
    @DisplayName("플레이어의 게임 결과를 반환한다.")
    @Test
    void returnPlayerResult() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)));
        final Gamers gamers = new Gamers(dealer, players);
        GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        final GameResult gameResult = GameResult.findGameResult(dealer, players.getPlayers().get(0));

        assertThat(gameResult).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("플레이어의 수익을 반환한다.")
    @Test
    void returnPlayerProfit() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)));
        final Gamers gamers = new Gamers(dealer, players);
        GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        final double playerProfit = GameResult.calculatePlayerProfit(dealer, players.getPlayers().get(0));

        assertThat(playerProfit).isEqualTo(0);
    }

    @DisplayName("딜러의 수익을 반환한다.")
    @Test
    void returnDealerProfit() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Dealer dealer = new Dealer();
        final Players players = new Players(List.of(
                Player.of("pobi", 10000)));
        final Gamers gamers = new Gamers(dealer, players);
        GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        final double dealerProfit = GameResult.calculateDealerProfit(dealer, players.getPlayers().get(0));

        assertThat(dealerProfit).isEqualTo(0);
    }
}
