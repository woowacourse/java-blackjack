package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;
import blackjack.domain.card.TestDeckFactory;
import blackjack.domain.gamer.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    /**
     * 카드 순서 :
     * Ace - HEART, SPADE, CLOVER, DIAMOND
     * 2 - HEART, SPADE, CLOVER, DIAMOND
     * ...
     * KING - HEART, SPADE, CLOVER, DIAMOND
     */
    @DisplayName("플레이어의 최종 수익을 반환한다.")
    @Test
    void returnPlayersProfit() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Players players = new Players(List.of(
                Player.of("pobi", 10000),
                Player.of("jason", 20000))
        );
        final Dealer dealer = new Dealer();
        final Gamers gamers = new Gamers(dealer, players);
        GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        Map<Name, BetAmount> playersProfit = gameBoard.getPlayersProfit();

        assertAll(
                () -> assertThat(playersProfit.get(new Name("pobi"))).isEqualTo(new BetAmount(0)),
                () -> assertThat(playersProfit.get(new Name("jason"))).isEqualTo(new BetAmount(-20000))
        );
    }

    @DisplayName("딜러의 최종 수익을 반환한다.")
    @Test
    void returnDealerProfits() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Players players = new Players(List.of(
                Player.of("pobi", 10000),
                Player.of("jason", 20000))
        );
        final Dealer dealer = new Dealer();
        final Gamers gamers = new Gamers(dealer, players);
        GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        double dealerProfit = gameBoard.getDealerProfit();

        assertThat(dealerProfit).isEqualTo(20000);
    }
}
