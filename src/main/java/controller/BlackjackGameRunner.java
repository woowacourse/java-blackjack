package controller;

import domain.card.CardMachine;
import domain.game.BlackjackGame;
import domain.game.BlackjackStatistics;
import domain.game.BlackjackJudge;
import domain.game.HitOrStand;
import domain.participant.BetAmount;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.List;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackGameRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        List<PlayerName> playerNames = inputPlayerNames();
        List<BetAmount> betAmounts = inputBetAmounts(playerNames);
        BlackjackGame blackjackGame = new BlackjackGame(
                new CardMachine(),
                new BlackjackJudge(),
                Participants.of(playerNames, betAmounts)
        );

        initializeGame(blackjackGame);
        inputHitOrStandOnPlayer(blackjackGame);
        while (blackjackGame.drawDealerCard()) {
            outputView.printDealerHit();
        }

        printBlackjackResult(blackjackGame);
        printBlackjackStatistics(blackjackGame);
    }

    private void initializeGame(BlackjackGame blackjackGame) {
        blackjackGame.drawInitialCards();

        List<Player> players = blackjackGame.getPlayers();
        outputView.printPlayers(players);
        outputView.printHandList(blackjackGame.getDealer(), players);
    }

    private List<PlayerName> inputPlayerNames() {
        List<String> names = Parser.parseInput(inputView.inputPlayers());
        List<PlayerName> playerNames = new ArrayList<>();
        for (String name : names) {
            playerNames.add(new PlayerName(name));
        }
        return playerNames;
    }

    private List<BetAmount> inputBetAmounts(List<PlayerName> playerNames) {
        List<BetAmount> betAmounts = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            String betAmountInput = inputView.inputBetAmount(playerName.name());
            betAmounts.add(new BetAmount(betAmountInput));
        }
        return betAmounts;
    }

    private void inputHitOrStandOnPlayer(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            inputHitOrStand(player, blackjackGame);
        }
    }

    private void inputHitOrStand(Player player, BlackjackGame blackjackGame) {
        String name = player.getName();
        HitOrStand hitOrStand = HitOrStand.from(inputView.inputHitOrStand(name));
        if (hitOrStand.isStand()) {
            outputView.printlnHand(name, player.getHand());
            return;
        }

        drawCardOnPlayer(name, blackjackGame);
    }

    private void drawCardOnPlayer(String name, BlackjackGame blackjackGame) {
        do {
            Player player = blackjackGame.updatePlayer(name);
            outputView.printlnHand(name, player.getHand());
        } while (canDrawContinue(name, blackjackGame));
    }

    private boolean canDrawContinue(String name, BlackjackGame blackjackGame) {
        return !blackjackGame.isPlayerBust(name) && HitOrStand.from(inputView.inputHitOrStand(name)).isHit();
    }

    private void printBlackjackResult(BlackjackGame blackjackGame) {
        outputView.printBlackjackResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private void printBlackjackStatistics(BlackjackGame blackjackGame) {
        BlackjackStatistics blackjackStatistics = blackjackGame.calculateStatistics();
        outputView.printBlackjackStatistics(blackjackStatistics);
    }
}
