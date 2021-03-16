package blackjack;

import blackjack.domain.BlackjackGame;
import blackjack.domain.gamer.Player;
import blackjack.domain.utils.RandomCardDeck;
import blackjack.dto.ResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final BlackjackGame blackjackGame = new BlackjackGame(InputView.requestNameAndMoney(), new RandomCardDeck());
        OutputView.printInitialCards(blackjackGame.getProcessDto());

        simulate(blackjackGame);

        printFinalView(blackjackGame);
    }

    private static void simulate(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            turnForPlayer(blackjackGame, player);
        }

        blackjackGame.turnForDealer();
        OutputView.printDealerGetCard();
    }

    private static void turnForPlayer(BlackjackGame blackjackGame, Player player) {
        while (player.isAbleToTake() && InputView.requestOneMoreCard(player.getName())) {
            OutputView.printPlayerCards(blackjackGame.turnForPlayer(player));
        }
    }

    private static void printFinalView(BlackjackGame blackjackGame) {
        OutputView.printCardsResult(blackjackGame.getProcessDto());

        final ResultDto resultDto = blackjackGame.getResultDto();
        OutputView.printOutcome(resultDto);
        OutputView.printProfit(resultDto);
    }

}
