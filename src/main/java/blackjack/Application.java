package blackjack;

import blackjack.domain.Game;
import blackjack.domain.gamer.Player;
import blackjack.dto.ResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Game game = new Game(InputView.requestNameAndMoney());
        OutputView.printCards(game.getProcessDto());

        simulate(game);

        printFinalView(game);
    }

    private static void simulate(Game game) {
        for (Player player : game.getPlayers()) {
            turnForPlayer(game, player);
        }

        game.turnForDealer();
        OutputView.printDealerGetCard();
    }

    private static void turnForPlayer(Game game, Player player) {
        while (player.isAbleToTake() && InputView.requestOneMoreCard(player.getName())) {
            OutputView.printCards(game.turnForPlayer(player));
        }
    }

    private static void printFinalView(Game game) {
        OutputView.printCardsResult(game.getProcessDto());

        final ResultDto resultDto = game.getResultDto();
        OutputView.printOutcome(resultDto);
        OutputView.printProfit(resultDto);
    }

}
