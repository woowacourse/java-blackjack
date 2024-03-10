package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Deck;
import blackjack.domain.dto.OutcomeDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameBoardTest {

    //@formatter:off
    /**
     * 카드 순서
     * Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * ...
     * KING카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     */
    //@formatter:on
    @DisplayName("딜러의 최종 승패를 알려준다.")
    @Test
    void informDealerOutcome() {
        final TestDeckFactory testDeckFactory = new TestDeckFactory();
        final Deck deck = testDeckFactory.create();
        final Players players = Players.from(List.of("pobi"));
        final Dealer dealer = Dealer.create();
        final Referee referee = new Referee(dealer.getCards());
        final GameBoard gameBoard = new GameBoard(deck, dealer, players);
        gameBoard.drawInitialPlayersCards();
        gameBoard.drawInitialDealerCards();

        final List<Outcome> dealerOutcome = gameBoard.getDealerOutcome(referee);

        assertThat(dealerOutcome).containsExactly(Outcome.PUSH);
    }

    @DisplayName("모든 플레이어의 최종 승패를 알려준다.")
    @Test
    void informPlayersOutcome() {
        final TestDeckFactory testDeckFactory = new TestDeckFactory();
        final Deck deck = testDeckFactory.create();
        final Players players = Players.from(List.of("pobi"));
        final Dealer dealer = Dealer.create();
        final Referee referee = new Referee(dealer.getCards());
        final GameBoard gameBoard = new GameBoard(deck, dealer, players);
        gameBoard.drawInitialPlayersCards();
        gameBoard.drawInitialDealerCards();

        final List<OutcomeDto> playerOutcomeDtos = gameBoard.getPlayerOutcomeDtos(referee);
        final Name name = playerOutcomeDtos.get(0).getName();
        final Outcome outcome = playerOutcomeDtos.get(0).getOutcome();

        assertAll(
                () -> assertThat(playerOutcomeDtos.size()).isEqualTo(players.getPlayers().size()),
                () -> assertThat(name).isEqualTo(new Name("pobi")),
                () -> assertThat(outcome).isEqualTo(Outcome.PUSH)
        );
    }
}
