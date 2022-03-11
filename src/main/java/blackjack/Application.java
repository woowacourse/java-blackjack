package blackjack;

import blackjack.dto.DealerDTO;
import blackjack.dto.EntryDTO;
import blackjack.dto.PlayersDTO;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        List<String> names = inputView.askEntryNames();

        Game game = new Game(names);
        game.start();

        final ResultView resultView = new ResultView();
        PlayersDTO playersDTO = PlayersDTO.from(game);
        resultView.printDeckInitialized(playersDTO);
        resultView.printInitializedDecks(playersDTO);

        do {
            game.toNextEntry();
            playTurn(inputView, game, resultView);
        } while (game.hasNextEntry());

        game.hitDealer();
        if (game.countCardsAddedToDealer() > 0) {
            resultView.printDealerHitCount(DealerDTO.from(game));
        }

        resultView.printScores(PlayersDTO.from(game));
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
        game.hitCurrentEntry();
        resultView.printDeck(entryDTO);
        playTurn(inputView, game, resultView);
    }
}
