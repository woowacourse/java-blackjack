package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.vo.GameResult;
import blackjack.dto.DealResultDto;
import blackjack.dto.GameResultDto;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Collections;
import java.util.List;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;
import static blackjack.view.InputView.readYesOrNo;

public class BlackjackController {
    public void run() {
        List<String> names = inputNames();
        BlackjackGame game = BlackjackGame.create(names, Collections::shuffle);

        game.betPlayers(name -> Integer.parseInt(InputView.readBetAmount(name)));

        game.deal();
        OutputView.printDealResult(DealResultDto.from(game));

        game.playPlayerTurns(
                name -> Parser.parseDrawInput(readYesOrNo(name)).isHit(),
                OutputView::printPlayerHand
        );

        game.playDealerTurn();
        OutputView.printDealerDrawMessage();

        OutputView.printGameResult(GameResultDto.from(game));

        List<GameResult> gameResults = game.gameResults();
        int dealerProfit = game.getDealerProfit(gameResults);
        OutputView.printFinalResult(gameResults, dealerProfit);
    }

    private List<String> inputNames() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input);
    }
}