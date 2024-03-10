package blackjack;

import blackjack.domain.GameBoard;
import blackjack.domain.Outcome;
import blackjack.domain.Referee;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckShuffleFactory;
import blackjack.dto.DealerResponseDto;
import blackjack.dto.OutcomeDto;
import blackjack.dto.OutcomesDto;
import blackjack.dto.PlayerResponseDto;
import blackjack.dto.PlayersResponseDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
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
        OutputView.printFinalState(DealerResponseDto.allCardToDto(gameBoard.getDealer()),
                PlayersResponseDto.toDto(gameBoard.getPlayers()));

        final Referee referee = new Referee(gameBoard.getDealerHand());
        final OutcomesDto dealerOutcome = Outcome.toDto(gameBoard.getDealerOutcome(referee));
        final List<OutcomeDto> playerOutcomes = gameBoard.getPlayerOutcomeDtos(referee);
        OutputView.printFinalOutcomes(dealerOutcome, playerOutcomes);
    }

    private static GameBoard createGameBoard() {
        final Deck deck = new Deck(new DeckShuffleFactory());
        final Dealer dealer = new Dealer();
        final Players players = Players.from(InputView.readPlayerNames());
        final Gamers gamers = new Gamers(dealer, players);
        return new GameBoard(deck, gamers);
    }

    private static void drawInitialCard(final GameBoard gameBoard) {
        gameBoard.drawInitialCards();
        OutputView.printInitialState(DealerResponseDto.firstCardToDto(gameBoard.getDealer()),
                PlayersResponseDto.toDto(gameBoard.getPlayers()));
    }

    private static void hit(final GameBoard gameBoard) {
        hitPlayers(gameBoard);
        hitDealer(gameBoard);
    }

    private static void hitPlayers(final GameBoard gameBoard) {
        for (final Player player : gameBoard.getPlayers()) {
            hitPlayer(gameBoard, player);
        }
    }

    private static void hitPlayer(final GameBoard gameBoard, final Player player) {
        while (gameBoard.canHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hit(player);
            OutputView.printCurrentState(PlayerResponseDto.toDto(player));
        }
    }

    private static void hitDealer(final GameBoard gameBoard) {
        while (gameBoard.canHit(gameBoard.getDealer())) {
            gameBoard.hit(gameBoard.getDealer());
            OutputView.printDealerDrawMessage();
        }
    }
}
