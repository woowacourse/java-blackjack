package blackjack;

import blackjack.dto.DealerDto;
import blackjack.dto.EntryDto;
import blackjack.dto.PlayersDto;
import blackjack.dto.ProfitsDto;
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
        return game -> inputView.askBetAmount(EntryDto.fromCurrentEntryOf(game));
    }

    private static void start(Game game, ResultView resultView) {
        game.start();

        PlayersDto playersDto = PlayersDto.from(game);
        resultView.printFirstHands(playersDto);
    }

    private static void playEntries(InputView inputView, ResultView resultView, Game game) {
        game.resetEntriesCursor();
        do {
            game.toNextEntry();
            playTurn(inputView, game, resultView);
        } while (game.hasNextEntry());
    }

    private static void playTurn(InputView inputView, Game game, ResultView resultView) {
        final EntryDto entryDto = EntryDto.fromCurrentEntryOf(game);
        if (game.canCurrentEntryHit()) {
            resultView.printBustMessage(entryDto);
            return;
        }
        if (!inputView.askForHit(entryDto)) {
            resultView.printFullHand(entryDto);
            return;
        }
        hitCurrentEntry(inputView, game, resultView);
    }

    private static void hitCurrentEntry(InputView inputView, Game game, ResultView resultView) {
        game.hitCurrentEntry();
        resultView.printFullHand(EntryDto.fromCurrentEntryOf(game));
        playTurn(inputView, game, resultView);
    }

    private static void playDealer(ResultView resultView, Game game) {
        game.hitDealer();
        if (game.countCardsAddedToDealer() > 0) {
            resultView.printDealerAddedCount(DealerDto.from(game));
        }
    }

    private static void showResults(ResultView resultView, Game game) {
        resultView.printResult(PlayersDto.from(game), ProfitsDto.from(game.getProfits()));
    }
}
