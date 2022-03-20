package blackjack;

import blackjack.dto.DealerDto;
import blackjack.dto.EntryDto;
import blackjack.dto.PlayersDto;
import blackjack.dto.ProfitsDto;
import blackjack.model.Game;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        Game game = new Game(inputView.askEntryNames());
        game.betMoney(askBetAmount(inputView));
        game.start(printFirstHands(resultView));
        game.playEntries(askForHit(inputView), printBustMessage(resultView), printFullHand(resultView));
        inputView.closeInput();
        game.showResults(printDealerAddedCount(resultView), printProfits(resultView));
    }

    private static Function<Game, Integer> askBetAmount(InputView inputView) {
        return game -> inputView.askBetAmount(EntryDto.fromCurrentEntryOf(game));
    }

    private static Consumer<Game> printFirstHands(ResultView resultView) {
        return game -> resultView.printFirstHands(PlayersDto.from(game));
    }

    private static Predicate<Game> askForHit(InputView inputView) {
        return game -> inputView.askForHit(EntryDto.fromCurrentEntryOf(game));
    }

    private static Consumer<Game> printBustMessage(ResultView resultView) {
        return game -> resultView.printBustMessage(EntryDto.fromCurrentEntryOf(game));
    }

    private static Consumer<Game> printFullHand(ResultView resultView) {
        return game -> resultView.printFullHand(EntryDto.fromCurrentEntryOf(game));
    }

    private static Consumer<Game> printDealerAddedCount(ResultView resultView) {
        return game -> resultView.printDealerAddedCount(DealerDto.from(game));
    }

    private static Consumer<Game> printProfits(ResultView resultView) {
        return game -> resultView.printProfits(PlayersDto.from(game), ProfitsDto.from(game.getProfits()));
    }
}
