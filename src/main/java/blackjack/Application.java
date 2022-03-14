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

        blackJackGame.giveStartCards();
        ResultView.printStartCardsDistributionResult(DealerDto.fromGame(blackJackGame), GamersDto.from(blackJackGame));

        blackJackGame.hitOrStayUntilPossible(InputView::inputHitOrStaySign, ResultView::printCurrentTurnHitResult);

        ResultView.printFinalScores(DealerDto.fromGame(blackJackGame), GamersDto.from(blackJackGame));
        ResultView.printMatchResult(DealerMatchResultsDto.from(blackJackGame),
                GamerMatchResultsDto.from(blackJackGame));
    }
}
