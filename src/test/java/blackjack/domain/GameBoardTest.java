package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
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
    @DisplayName("딜러의 최종 승패를 알려준다.")
    @Test
    void informDealerOutcome() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Players players = Players.from(List.of("pobi"));
        final Dealer dealer = new Dealer();
        final Gamers gamers = new Gamers(dealer, players);
        final Referee referee = new Referee(dealer.getHand());
        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        final List<Outcome> dealerOutcome = gameBoard.getDealerOutcome(referee);

        assertThat(dealerOutcome).containsExactly(Outcome.PUSH);
    }

    @DisplayName("모든 플레이어의 최종 승패를 알려준다.")
    @Test
    void informPlayersOutcome() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Players players = Players.from(List.of("pobi"));
        final Dealer dealer = new Dealer();
        final Gamers gamers = new Gamers(dealer, players);
        final Referee referee = new Referee(dealer.getHand());
        final GameBoard gameBoard = new GameBoard(deck, gamers);
        gameBoard.drawInitialCards();

        final Map<Player, Outcome> playerOutcomeMap = gameBoard.getPlayersOutcome(referee);

        final Player firstPlayer = gameBoard.getPlayers().get(0);
        final Outcome outcome = playerOutcomeMap.get(firstPlayer);

        assertAll(
                () -> assertThat(playerOutcomeMap.size()).isEqualTo(players.getPlayers().size()),
                () -> assertThat(outcome).isEqualTo(Outcome.PUSH)
        );
    }
}
