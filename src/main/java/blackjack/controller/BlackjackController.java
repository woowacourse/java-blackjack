package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.vo.GameResult;
import blackjack.dto.DealResultDto;
import blackjack.dto.GameResultDto;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static blackjack.util.Parser.splitDelimiter;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputNames();
        outputView.printEmptyLine();
        BlackjackGame game = BlackjackGame.create(names, Collections::shuffle);

        game.betPlayers(name -> {
            String amount = inputView.readBetAmount(name);
            outputView.printEmptyLine();
            return amount;
        });

        game.deal();
        outputView.printDealResult(DealResultDto.from(game));

        game.playPlayerTurns(
                name -> Parser.parseDrawInput(inputView.readYesOrNo(name)).isHit(),
                outputView::printPlayerHand
        );
        outputView.printEmptyLine();

        game.playDealerTurn();
        outputView.printDealerDrawMessage();

        outputView.printGameResult(GameResultDto.from(game));

        List<GameResult> gameResults = game.calculatePlayerProfits();
        BigDecimal dealerProfit = game.calculateDealerProfit(gameResults);
        outputView.printFinalResult(gameResults, dealerProfit);
    }

    private List<String> inputNames() {
        String input = inputView.readPlayNames();
        return splitDelimiter(input);
    }
}