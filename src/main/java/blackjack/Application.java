package blackjack;

import blackjack.domain.GameBoard;
import blackjack.domain.Outcome;
import blackjack.domain.Referee;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckShuffleFactory;
import blackjack.domain.dto.DealerDto;
import blackjack.domain.dto.OutcomeDto;
import blackjack.domain.dto.OutcomesDto;
import blackjack.domain.dto.PlayersDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        final GameBoard gameBoard = createGameBoard();
        drawInitialCard(gameBoard);

        hit(gameBoard);
        OutputView.printFinalState(gameBoard.getDealerFinalState(), gameBoard.getPlayersFinalState());

        final Referee referee = new Referee(gameBoard.getDealerCards());
        final OutcomesDto dealerOutcome = Outcome.toDto(gameBoard.getDealerOutcome(referee));
        final List<OutcomeDto> playerOutcomes = gameBoard.getPlayerOutcomeDtos(referee);
        OutputView.printFinalOutcomes(dealerOutcome, playerOutcomes);
    }

    private static GameBoard createGameBoard() {
        final Deck deck = new Deck(new DeckShuffleFactory());
        final Dealer dealer = new Dealer();
        final Players players = Players.from(InputView.readPlayerNames());
        return new GameBoard(deck, dealer, players);
    }

    private static void drawInitialCard(final GameBoard gameBoard) {
        final DealerDto dealerDto = gameBoard.drawInitialDealerCards();
        final PlayersDto playersDto = gameBoard.drawInitialPlayersCards();

        OutputView.printInitialState(dealerDto, playersDto);
    }

    private static void hit(final GameBoard gameBoard) {
        hitPlayers(gameBoard, gameBoard.getPlayers());
        hitDealer(gameBoard, gameBoard.getDealer());
    }

    private static void hitPlayers(final GameBoard gameBoard, final Players players) {
        for (final Player player : players.getPlayers()) {
            hitPlayer(gameBoard, player);
        }
    }

    private static void hitPlayer(final GameBoard gameBoard, final Player player) {
        while (gameBoard.isHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hit(player);
            OutputView.printCurrentState(player.toDto());
        }
    }

    private static void hitDealer(final GameBoard gameBoard, final Dealer dealer) {
        while (gameBoard.isHit(dealer)) {
            gameBoard.hit(dealer);
            OutputView.printDealerDrawMessage();
        }
    }
}
