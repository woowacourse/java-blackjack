package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.vo.MatchResult;
import blackjack.dto.DealResultDto;
import blackjack.dto.GameResultDto;
import blackjack.util.Parser;
import blackjack.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;
import static blackjack.view.InputView.readYesOrNo;

public class BlackjackController {
    public void run() {
        List<String> names = inputNames();
        BlackjackGame game = BlackjackGame.create(names, Collections::shuffle);

        game.deal();
        OutputView.printDealResult(DealResultDto.from(game));

        game.playPlayerTurns(
                name -> Parser.parseDrawInput(readYesOrNo(name)).isHit(),
                OutputView::printPlayerHand
        );

        game.playDealerTurn();
        OutputView.printDealerDrawMessage();

        OutputView.printGameResult(GameResultDto.from(game));

        Map<String, MatchResult> matchResults = game.matchResults();
        OutputView.printFinalResult(matchResults);
    }

    private List<String> inputNames() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input);
    }
}