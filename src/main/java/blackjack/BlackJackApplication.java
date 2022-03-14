package blackjack;

import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

import static blackjack.domain.gamer.Gamer.INIT_DISTRIBUTION_COUNT;

public class BlackJackApplication {
    public static void main(String[] args) {
        List<String> names = InputView.getNames();
        BlackJackGame blackJackGame = new BlackJackGame(names);

        OutputView.printFirstCards(blackJackGame.getDealer(), blackJackGame.getPlayers());

        drawAdditionalCard(names, blackJackGame);

        printAdditionalDrawDealer(blackJackGame);
        OutputView.printFinalCards(blackJackGame.getDealer(), blackJackGame.getPlayers());
        OutputView.printFinalResult(blackJackGame.createResult());
    }

    private static void printAdditionalDrawDealer(BlackJackGame controller) {
        controller.distributeAdditionalToDealer();
        OutputView.printAdditionalDrawDealer(controller.getDealerCardSize() - INIT_DISTRIBUTION_COUNT);
    }

    private static void drawAdditionalCard(List<String> names, BlackJackGame controller) {
        for (String name : names) {
            drawCardToPlayer(controller, name);
        }
    }

    private static void drawCardToPlayer(BlackJackGame blackJackGame, String name) {
        while (blackJackGame.isDrawPossible(name, InputView::getAnswerOfAdditionalDraw)) {
            blackJackGame.distributeCardToPlayer(name);
            Player playerDtoByName = blackJackGame.findPlayerByName(name);
            OutputView.printPlayerCard(playerDtoByName);
        }
    }
}
