package blackjack.controller;

import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.dto.DealResult;
import blackjack.dto.GameResult;
import blackjack.dto.PlayerHandResult;
import blackjack.service.BlackjackGame;
import blackjack.service.RandomShuffleStrategy;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;

public class BlackjackController {
    public void run(){
        List<String> names = inputName();
        RandomShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();
        BlackjackGame blackjackGame = BlackjackGame.create(names, shuffleStrategy);

        DealResult dealResult = deal(blackjackGame);
        OutputView.printDealResult(dealResult);

        playPlayerTurn(blackjackGame);

        while(blackjackGame.canDealerHit()){
            OutputView.printDealerDrawMessage();
            blackjackGame.dealerDraw();
        }

        GameResult gameResult = blackjackGame.generateGameResult();
        OutputView.printGameResult(gameResult);

        Map<Player, MatchResult> playerFinalResult = blackjackGame.getPlayerFinalResult();
        Map<String, Long> dealerFinalResult = blackjackGame.getDealerFinalResult(playerFinalResult);
        OutputView.printFinalResult(playerFinalResult, dealerFinalResult);

    }

    private List<String> inputName() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input);
    }

    private DealResult deal(BlackjackGame blackjackGame) {
        blackjackGame.deal();
        return DealResult.from(blackjackGame.getPlayers(), blackjackGame.getDealer());
    }

    private void playPlayerTurn(BlackjackGame blackjackGame){
        for (int i = 0; i < blackjackGame.playerCount(); i++) {
            playTurn(blackjackGame, i);
        }
    }

    private void playTurn(BlackjackGame blackjackGame, int index){
        while (blackjackGame.canPlayerHit(index)) {
            String answer = InputView.readYesOrNo(blackjackGame.playerNameByIndex(index));
            if ("n".equals(answer)) {
                break;
            }
            PlayerHandResult playerHandResult = PlayerHandResult.from(blackjackGame.playerDraw(index));
            OutputView.printCurrentPlayerHand(playerHandResult);
        }
        System.out.println();
    }
}