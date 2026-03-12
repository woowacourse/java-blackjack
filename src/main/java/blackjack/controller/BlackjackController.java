package blackjack.controller;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;

import blackjack.domain.BlackjackGame;
import blackjack.domain.MatchResult;
import blackjack.domain.Player;
import blackjack.dto.DealResult;
import blackjack.dto.GameResult;
import blackjack.dto.PlayerHandResult;
import blackjack.service.RandomShuffleStrategy;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = readyBlackjackGame();
        dealAndPrintResult(blackjackGame);
        playPlayerTurn(blackjackGame);
        playDealerTurn(blackjackGame);
        generateAndPrintPlayResult(blackjackGame);
    }

    private BlackjackGame readyBlackjackGame() {
        List<String> playerNames = inputName();
        RandomShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();
        return BlackjackGame.create(playerNames, shuffleStrategy);
    }

    private List<String> inputName() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input);
    }

    private void dealAndPrintResult(BlackjackGame blackjackGame) {
        blackjackGame.deal();
        DealResult dealResult = DealResult.from(blackjackGame.getPlayers(), blackjackGame.getDealer());
        OutputView.printDealResult(dealResult);
    }

    private void playPlayerTurn(BlackjackGame blackjackGame) {
        for (int i = 0; i < blackjackGame.playerCount(); i++) {
            playTurn(blackjackGame, i);
        }
    }

    private void playTurn(BlackjackGame blackjackGame, int index) {
        while (blackjackGame.canPlayerHit(index) && playerChoosesHit(blackjackGame, index)) {
            hitAndPrintPlayerHand(blackjackGame, index);
        }
        OutputView.printLineBreak();
    }

    private boolean playerChoosesHit(BlackjackGame blackjackGame, int index) {
        return inputYesOrNo(blackjackGame.playerNameByIndex(index));
    }

    private void hitAndPrintPlayerHand(BlackjackGame blackjackGame, int index) {
        PlayerHandResult playerHandResult = PlayerHandResult.from(blackjackGame.playerDraw(index));
        OutputView.printCurrentPlayerHand(playerHandResult);
    }

    private boolean inputYesOrNo(String playerName) {
        String input = InputView.readYesOrNo(playerName);
        return Parser.parseAnswer(input);
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerHit()) {
            OutputView.printDealerDrawMessage();
            blackjackGame.dealerDraw();
        }
    }

    private void generateAndPrintPlayResult(BlackjackGame blackjackGame) {
        GameResult gameResult = blackjackGame.generateGameResult();
        OutputView.printGameResult(gameResult);

        Map<Player, MatchResult> playerFinalResult = blackjackGame.getPlayerFinalResult();
        Map<String, Long> dealerFinalResult = blackjackGame.getDealerFinalResult(playerFinalResult);
        OutputView.printFinalResult(playerFinalResult, dealerFinalResult);
    }
}