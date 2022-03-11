package blackjack;

import java.util.List;

import blackjack.controller.BlackJackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        List<String> names = InputView.getNames();
        BlackJackController controller = new BlackJackController(names);

        OutputView.printFirstCards(controller.getDealerDto(), controller.getPlayerDtos());

        controller.askHitOrStay(names, InputView::getAnswerOfAdditionalDraw, OutputView::printPlayerCard);

        OutputView.printAdditionalDrawDealer(controller.getDealerAdditionalCardCount());
        OutputView.printFinalCards(controller.getDealerDto(), controller.getPlayerDtos());
        OutputView.printFinalResult(controller.getGamerResult());
    }
}
