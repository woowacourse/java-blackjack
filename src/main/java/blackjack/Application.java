package blackjack;

import blackjack.dto.DealerDto;
import blackjack.dto.GamerMatchResultsDto;
import blackjack.dto.GamersDto;
import blackjack.model.BlackJackGame;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> names = InputView.inputNames();
        BlackJackGame blackJackGame = new BlackJackGame(names);

        blackJackGame.giveStartCards();
        ResultView.printStartCardsDistributionResult(DealerDto.fromGame(blackJackGame), GamersDto.from(blackJackGame));

        blackJackGame.hitOrStayUntilPossible(InputView::inputHitOrStaySign, ResultView::printCurrentTurnHitResult);

        ResultView.printFinalScores(DealerDto.fromGame(blackJackGame), GamersDto.from(blackJackGame));
        ResultView.printMatchResult(GamerMatchResultsDto.from(blackJackGame));
    }
}
