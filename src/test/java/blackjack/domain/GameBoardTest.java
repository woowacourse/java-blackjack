package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    //@formatter:off
    /**
     * 카드 저장 순서
     * Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * ...
     * KING카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     *
     * 카드 pop 순서는 카드 저장 순서의 역순이다.
     */
    //@formatter:on
    @DisplayName("딜러의 최종 승패를 알려준다.")
    @Test
    void informDealerOutcome() {
        final TestDeckFactory testDeckFactory = new TestDeckFactory();
        final Deck deck = testDeckFactory.create();
        final Players players = Players.from(List.of("pobi"));
        final Dealer dealer = Dealer.of(deck);
        final GameBoard gameBoard = new GameBoard(dealer, players);
        gameBoard.drawInitialPlayersHand();
        gameBoard.drawInitialDealerHand();

        final List<Outcome> dealerOutcome = gameBoard.getDealerOutcome();

        assertThat(dealerOutcome).containsExactly(Outcome.PUSH);
    }

    @DisplayName("모든 플레이어의 최종 승패를 알려준다.")
    @Test
    void informPlayersOutcome() {
        final TestDeckFactory testDeckFactory = new TestDeckFactory();
        final Deck deck = testDeckFactory.create();
        final Players players = Players.from(List.of("pobi", "jason"));
        final Dealer dealer = Dealer.of(deck);
        final GameBoard gameBoard = new GameBoard(dealer, players);
        gameBoard.drawInitialDealerHand();
        gameBoard.drawInitialPlayersHand();

        final Map<Name, Outcome> playerOutcomes = gameBoard.getPlayerOutcomes();

        assertAll(
                () -> assertThat(playerOutcomes.size()).isEqualTo(players.getPlayers().size()),
                () -> assertThat(playerOutcomes.get(new Name("pobi"))).isEqualTo(Outcome.PUSH),
                () -> assertThat(playerOutcomes.get(new Name("jason"))).isEqualTo(Outcome.PUSH)
        );
    }
}
