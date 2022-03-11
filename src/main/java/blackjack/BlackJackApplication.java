package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.controller.dto.GamerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        List<String> names = InputView.getNames();
        BlackJackController controller = new BlackJackController(names);

        OutputView.printFirstCards(controller.getDealerDto(), controller.getPlayerDtos());

        drawAdditionalCard(names, controller);

        OutputView.printAdditionalDrawDealer(controller.getDealerAdditionalCardCount());
        OutputView.printFinalCards(controller.getDealerDto(), controller.getPlayerDtos());
        OutputView.printFinalResult(controller.getGamerResult());
    }

    private static void drawAdditionalCard(List<String> names, BlackJackController controller) {
        for (String name : names) {
            drawCardToPlayer(controller, name);
        }
    }

    private static void drawCardToPlayer(BlackJackController controller, String name) {
        while (controller.isDrawPossible(name, InputView::getAnswerOfAdditionalDraw)) {
            controller.requestPlayerDrawCard(name);
            GamerDto playerDtoByName = controller.findPlayerByName(name);
            OutputView.printPlayerCard(playerDtoByName);
        }
    }
}
