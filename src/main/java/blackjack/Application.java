package blackjack;

import blackjack.dto.DealerDTO;
import blackjack.dto.EntryDTO;
import blackjack.dto.PlayersDTO;
import blackjack.dto.ProfitsDTO;
import blackjack.model.Game;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.function.Function;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        Game game = new Game(inputView.askEntryNames());
        betMoney(inputView, game);
        start(game, resultView);
        playEntries(inputView, resultView, game);
        inputView.closeInput();
        playDealer(resultView, game);
        showResults(resultView, game);
    }

    private static void betMoney(InputView inputView, Game game) {
        do {
            game.toNextEntry();
            game.betToCurrentEntry(giveMoneyInput(inputView));
        } while (game.hasNextEntry());
    }

    private static Function<Game, Integer> giveMoneyInput(InputView inputView) {
        return game -> inputView.askBetAmount(EntryDTO.fromCurrentEntryOf(game));
    }

    private static void start(Game game, ResultView resultView) {
        game.start();

        PlayersDTO playersDTO = PlayersDTO.from(game);
        resultView.printHandInitialized(playersDTO);
        resultView.printInitializedHands(playersDTO);
    }

    private static void playEntries(InputView inputView, ResultView resultView, Game game) {
        game.resetEntriesCursor();
        do {
            game.toNextEntry();
            playTurn(inputView, game, resultView);
        } while (game.hasNextEntry());
    }

    private static void playTurn(InputView inputView, Game game, ResultView resultView) {
        EntryDTO entryDTO = EntryDTO.fromCurrentEntryOf(game);
        if (game.isCurrentEntryBust()) {
            resultView.printBustMessage(entryDTO);
            return;
        }
        if (!inputView.askForHit(entryDTO)) {
            resultView.printHand(entryDTO);
            return;
        }
        hitCurrentEntry(inputView, game, resultView);
    }

    private static void hitCurrentEntry(InputView inputView, Game game, ResultView resultView) {
        game.hitCurrentEntry();
        resultView.printHand(EntryDTO.fromCurrentEntryOf(game));
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
        resultView.printProfits(ProfitsDTO.from(game.getProfits()));
    }
}
