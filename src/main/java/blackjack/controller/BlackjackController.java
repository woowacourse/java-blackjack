package blackjack.controller;

import static blackjack.util.Parser.splitDelimiter;
import static blackjack.view.InputView.readPlayNames;

import blackjack.domain.BetAmount;
import blackjack.domain.BlackjackGame;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.DealResult;
import blackjack.dto.GameResult;
import blackjack.dto.PlayerHandResult;
import blackjack.service.RandomShuffleStrategy;
import blackjack.util.Parser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = readyBlackjackGame();
        dealAndPrintResult(blackjackGame);
        playPlayerTurn(blackjackGame);
        playDealerTurn(blackjackGame);
        generateAndPrintProfitResult(blackjackGame);
    }

    private BlackjackGame readyBlackjackGame() {
        List<Name> names = inputNames();
        Players players = inputBettingAmounts(names);
        return BlackjackGame.create(players, new RandomShuffleStrategy());
    }

    private List<Name> inputNames() {
        String input = readPlayNames();
        Parser.notEmpty(input);
        return splitDelimiter(input).stream()
                .map(Name::of)
                .toList();
    }

    private Players inputBettingAmounts(List<Name> names) {
        List<Player> players = new ArrayList<>();
        for (Name name : names) {
            OutputView.printLineBreak();
            BetAmount betAmount = BetAmount.of(Parser.parseAmount(InputView.readBettingAmount(name.getName())));
            players.add(Player.of(name, betAmount));
        }
        OutputView.printLineBreak();
        return Players.of(players);
    }

    private void dealAndPrintResult(BlackjackGame blackjackGame) {
        blackjackGame.deal();
        DealResult dealResult = DealResult.from(blackjackGame);
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

    private void generateAndPrintProfitResult(BlackjackGame blackjackGame) {
        printGameResult(blackjackGame);
        printProfitResult(blackjackGame);
    }

    private void printGameResult(BlackjackGame blackjackGame) {
        GameResult gameResult = blackjackGame.generateGameResult();
        OutputView.printGameResult(gameResult);
    }


    private void printProfitResult(BlackjackGame blackjackGame) {
        OutputView.printFinalResult(blackjackGame.calculateProfits());
    }
}