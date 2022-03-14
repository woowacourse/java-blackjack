package blackjack;

import blackjack.dto.DealerDto;
import blackjack.dto.DealerMatchResultsDto;
import blackjack.dto.GamerMatchResultsDto;
import blackjack.dto.GamersDto;
import blackjack.model.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(InputView.inputPlayerNames());

        blackJackGame.start();
        ResultView.printStartResult(blackJackGame.getDealer(), blackJackGame.getGamers2());

        blackJackGame.hitOrStayUntilPossible(InputView::inputHitOrStaySign, ResultView::printCurrentTurnHitResult);

        ResultView.printFinalScores(DealerDto.from(blackJackGame.getDealer()), GamersDto.from(blackJackGame));
        ResultView.printMatchResult(DealerMatchResultsDto.from(blackJackGame),
                GamerMatchResultsDto.from(blackJackGame));
    }
}
