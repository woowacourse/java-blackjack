package blackjack;

import blackjack.dto.DealerDto;
import blackjack.dto.EntryDto;
import blackjack.dto.PlayersDto;
import blackjack.dto.ProfitsDto;
import blackjack.model.Game;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        Game game = new Game(inputView.askEntryNames());
        betMoney(game, inputView);
        giveFirstHands(resultView, game);
        playEntries(inputView, resultView, game);
        inputView.closeInput();
        hitDealer(game, resultView);
        resultView.printProfits(PlayersDto.from(game), ProfitsDto.from(game.calculateProfits()));
    }

    private static void betMoney(Game game, InputView inputView) {
        do {
            game.toNextEntry();
            game.betToCurrentEntry(inputView.askBetAmount(EntryDto.fromCurrentEntryOf(game)));
        } while (game.hasNextEntry());
        game.toFirstEntry();
    }

    private static void giveFirstHands(ResultView resultView, Game game) {
        game.giveFirstHands();
        resultView.printFirstHands(PlayersDto.from(game));
    }

    private static void playEntries(InputView inputView, ResultView resultView, Game game) {
        do {
            game.toNextEntry();
            playTurn(game, inputView, resultView);
        } while (game.hasNextEntry());
    }

    private static void playTurn(Game game, InputView inputView, ResultView resultView) {
        if (!game.canCurrentEntryHit()) {
            resultView.printBustMessage(EntryDto.fromCurrentEntryOf(game));
            return;
        }
        hitCurrentEntry(game, inputView, resultView);
    }

    private static void hitCurrentEntry(Game game, InputView inputView, ResultView resultView) {
        if (inputView.askForHit(EntryDto.fromCurrentEntryOf(game))) {
            game.hitCurrentEntry();
            resultView.printFullHand(EntryDto.fromCurrentEntryOf(game));
            playTurn(game, inputView, resultView);
        }
    }

    private static void hitDealer(Game game, ResultView resultView) {
        if (game.hitDealer()) {
            resultView.printDealerAddedCount(DealerDto.from(game));
        }
    }
}
