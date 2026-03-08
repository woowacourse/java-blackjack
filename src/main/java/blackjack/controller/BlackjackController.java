package blackjack.controller;

import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.dto.DealResultDto;
import blackjack.dto.GameResultDto;
import blackjack.dto.PlayerHandDto;
import blackjack.domain.BlackjackGame;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;

public class BlackjackController {
    public void run() {
        List<String> names = inputNames();
        BlackjackGame blackjackGame = BlackjackGame.create(names, Collections::shuffle);

        DealResultDto dealResultDto = deal(blackjackGame);
        OutputView.printDealResult(dealResultDto);

        playPlayerTurn(blackjackGame);

        while (blackjackGame.canDealerHit()) {
            OutputView.printDealerDrawMessage();
            blackjackGame.dealerDraw();
        }

        GameResultDto gameResultDto = blackjackGame.generateGameResult();
        OutputView.printGameResult(gameResultDto);

        Map<Player, MatchResult> playerFinalResult = blackjackGame.getPlayerFinalResult();
        Map<String, Long> dealerFinalResult = blackjackGame.getDealerFinalResult(playerFinalResult);
        OutputView.printFinalResult(playerFinalResult, dealerFinalResult);

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
        while (blackjackGame.canPlayerHit(index)) {
            String answer = InputView.readYesOrNo(blackjackGame.playerNameByIndex(index));
            if ("n".equals(answer)) {
                break;
            }
            PlayerHandDto playerHandDto = PlayerHandDto.from(blackjackGame.playerDraw(index));
            OutputView.printCurrentPlayerHand(playerHandDto);
        }
        System.out.println();
    }
}