package blackjack.controller;

import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.dto.DealResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.PlayerHandDto;
import blackjack.domain.BlackjackGame;
import blackjack.util.Parser;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;
import static blackjack.view.InputView.readYesOrNo;
import static blackjack.view.OutputView.printCurrentPlayerHand;
import static blackjack.view.OutputView.printDealResult;
import static blackjack.view.OutputView.printDealerDrawMessage;
import static blackjack.view.OutputView.printEmptyLine;
import static blackjack.view.OutputView.printFinalResult;
import static blackjack.view.OutputView.printGameResult;

public class BlackjackController {
    public void run() {
        List<String> names = inputNames();
        BlackjackGame blackjackGame = BlackjackGame.create(names, Collections::shuffle);

        DealResultDto dealResultDto = deal(blackjackGame);
        printDealResult(dealResultDto);

        playPlayerTurn(blackjackGame);
        playDealerTurn(blackjackGame);

        GameResultDto gameResultDto = blackjackGame.generateGameResult();
        printGameResult(gameResultDto);

        Map<Player, MatchResult> playerFinalResult = blackjackGame.getPlayerFinalResult();
        Map<String, Long> dealerFinalResult = blackjackGame.getDealerFinalResult(playerFinalResult);
        printFinalResult(playerFinalResult, dealerFinalResult);
    }

    private List<String> inputNames() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input);
    }

    private DealResultDto deal(BlackjackGame blackjackGame) {
        blackjackGame.deal();
        return DealResultDto.from(blackjackGame.getPlayers(), blackjackGame.getDealer());
    }

    private void playPlayerTurn(BlackjackGame blackjackGame) {
        for (int i = 0; i < blackjackGame.playerCount(); i++) {
            playTurn(blackjackGame, i);
        }
    }

    private void playTurn(BlackjackGame blackjackGame, int index) {
        while (blackjackGame.canPlayerHit(index) && wantsToHit(blackjackGame, index)) {
            hitAndPrintHand(blackjackGame, index);
        }
        printEmptyLine();
    }

    private boolean wantsToHit(BlackjackGame blackjackGame, int index) {
        String answer = readYesOrNo(blackjackGame.playerNameByIndex(index));
        DrawCommand drawCommand = Parser.parseDrawInput(answer);
        return drawCommand.isHit();
    }

    private void hitAndPrintHand(BlackjackGame blackjackGame, int index){
        PlayerHandDto playerHandDto = PlayerHandDto.from(blackjackGame.playerDraw(index));
        printCurrentPlayerHand(playerHandDto);
    }

    private void playDealerTurn(BlackjackGame blackjackGame){
        while (blackjackGame.canDealerHit()) {
            printDealerDrawMessage();
            blackjackGame.dealerDraw();
        }
    }
}