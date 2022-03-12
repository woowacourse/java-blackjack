package blackjack;

import blackjack.domain.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = BlackJackGame.start(InputView.getNames());
        OutputView.printFirstCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());

        blackJackGame.askHitOrStay(InputView::getAnswerOfAdditionalDraw, OutputView::printPlayerCard);
        OutputView.printAdditionalDrawDealer(blackJackGame.distributeAdditionalToDealer());

        OutputView.printFinalCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());
        OutputView.printFinalResult(blackJackGame.createResult());
    }
}
