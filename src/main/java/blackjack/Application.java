package blackjack;

import blackjack.dto.DealerDTO;
import blackjack.dto.EntryDTO;
import blackjack.dto.PlayersDTO;
import blackjack.dto.ResultsDTO;
import blackjack.model.Game;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        Game game = startGame(inputView, resultView);
        playEntries(inputView, resultView, game);
        playDealer(resultView, game);
        showResults(resultView, game);
    }

    private static Game startGame(InputView inputView, ResultView resultView) {
        List<String> names = inputView.askEntryNames();
        Game game = new Game(names);
        game.start();

        PlayersDTO playersDTO = PlayersDTO.from(game);
        resultView.printDeckInitialized(playersDTO);
        resultView.printInitializedDecks(playersDTO);
        return game;
    }

    private static void playEntries(InputView inputView, ResultView resultView, Game game) {
        do {
            game.toNextEntry();
            playTurn(inputView, game, resultView);
        } while (game.hasNextEntry());
    }

    private static void playTurn(InputView inputView, Game game, ResultView resultView) {
        EntryDTO entryDTO = EntryDTO.fromCurrent(game);
        if (game.isCurrentEntryBust()) {
            resultView.printBustMessage(entryDTO);
            return;
        }
        if (!inputView.askForHit(entryDTO)) {
            resultView.printDeck(entryDTO);
            return;
        }
        hitCurrentEntry(inputView, game, resultView);
    }

    private static void hitCurrentEntry(InputView inputView, Game game, ResultView resultView) {
        game.hitCurrentEntry();
        resultView.printDeck(EntryDTO.fromCurrent(game));
        playTurn(inputView, game, resultView);
    }

    private static void playDealer(ResultView resultView, Game game) {
        game.hitDealer();
        if (game.countCardsAddedToDealer() > 0) {
            resultView.printDealerHitCount(DealerDTO.from(game));
        }
    }

    private static void showResults(ResultView resultView, Game game) {
        resultView.printScores(PlayersDTO.from(game));
        resultView.printResults(ResultsDTO.from(game.getResults()), DealerDTO.from(game));
    }
}
