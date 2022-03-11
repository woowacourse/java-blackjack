package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackController controller = new BlackJackController(InputView::getNames);
        OutputView.printFirstCards(controller.getDealerDto(), controller.getPlayerDtos());

        controller.askHitOrStay(InputView::getAnswerOfAdditionalDraw, OutputView::printPlayerCard);

        OutputView.printAdditionalDrawDealer(controller.getDealerAdditionalCardCount());
        OutputView.printFinalCards(controller.getDealerDto(), controller.getPlayerDtos());
        OutputView.printFinalResult(controller.getGamerResult());
    }
}
