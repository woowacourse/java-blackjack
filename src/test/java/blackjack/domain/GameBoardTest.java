package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import java.util.List;
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
    @DisplayName("플레이어의 최종 승패를 계산한다.")
    @Test
    void calculatePlayerOutcome() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Players players = Players.from(List.of("pobi"));
        final Dealer dealer = Dealer.create();
        final Referee referee = new Referee(dealer.getCards());
        final GameBoard gameBoard = new GameBoard(deck, dealer, players);
        gameBoard.drawInitialPlayersCards();
        gameBoard.drawInitialDealerCards();

        final Outcome outcome = gameBoard.calculateOutcome(referee, new Name("pobi"));

        assertThat(outcome).isEqualTo(Outcome.PUSH);
    }

    @DisplayName("모든 플레이어의 최종 승패를 계산한다.")
    @Test
    void calculateAllPlayersOutcome() {
        final Deck deck = new Deck(new TestDeckFactory());
        final Players players = Players.from(List.of("pobi", "jason"));
        final Dealer dealer = Dealer.create();
        final Referee referee = new Referee(dealer.getCards());
        final GameBoard gameBoard = new GameBoard(deck, dealer, players);
        gameBoard.drawInitialPlayersCards();
        gameBoard.drawInitialDealerCards();

        final List<Outcome> outcome = gameBoard.calculateOutcomes(referee);

        assertThat(outcome).containsExactly(Outcome.WIN, Outcome.WIN);
    }
}
