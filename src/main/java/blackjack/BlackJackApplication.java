package blackjack;

import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        List<String> names = InputView.getNames();
        BlackJackGame controller = new BlackJackGame(names);

        OutputView.printFirstCards(controller.getDealer(), controller.getPlayers());

        drawAdditionalCard(names, controller);

        printAddtionalDrawDealr(controller);
        OutputView.printFinalCards(controller.getDealer(), controller.getPlayers());
        OutputView.printFinalResult(controller.createResult());
    }

    private static void printAddtionalDrawDealr(BlackJackGame controller) {
        controller.distributeAdditionalToDealer();
        OutputView.printAdditionalDrawDealer(controller.getDealerCardSize());
    }

    private static void drawAdditionalCard(List<String> names, BlackJackGame controller) {
        for (String name : names) {
            drawCardToPlayer(controller, name);
        }
    }

    private static void drawCardToPlayer(BlackJackGame controller, String name) {
        while (controller.isDrawPossible(name, InputView::getAnswerOfAdditionalDraw)) {
            controller.distributeCardToPlayer(name);
            Player playerDtoByName = controller.findPlayerByName(name);
            OutputView.printPlayerCard(playerDtoByName);
        }
    }
}
