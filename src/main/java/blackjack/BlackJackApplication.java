package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        List<String> names = InputView.getNames();
        BlackJackController controller = new BlackJackController(names);

        OutputView.printFirstCards(controller.getDealer(), controller.getPlayers());

        drawAdditionalCard(names, controller);

        printAddtionalDrawDealr(controller);
        OutputView.printFinalCards(controller.getDealer(), controller.getPlayers());
        OutputView.printFinalResult(controller.createResult());
    }

    private static void printAddtionalDrawDealr(BlackJackController controller) {
        controller.distributeAdditionalToDealer();
        OutputView.printAdditionalDrawDealer(controller.getDealerCardSize());
    }

    private static void drawAdditionalCard(List<String> names, BlackJackController controller) {
        for (String name : names) {
            drawCardToPlayer(controller, name);
        }
    }

    private static void drawCardToPlayer(BlackJackController controller, String name) {
        while (controller.isDrawPossible(name, InputView::getAnswerOfAdditionalDraw)) {
            controller.distributeCardToPlayer(name);
            Player playerDtoByName = controller.findPlayerByName(name);
            OutputView.printPlayerCard(playerDtoByName);
        }
    }
}
