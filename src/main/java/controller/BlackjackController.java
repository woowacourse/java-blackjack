package controller;

import domain.blackjack.BlackjackAction;
import domain.blackjack.BlackjackGame;
import domain.blackjack.TotalProfit;
import domain.card.DeckFactory;
import domain.money.BetAmount;
import domain.participant.Participant;
import domain.participant.ParticipantName;
import domain.participant.Player;
import domain.participant.PlayerNames;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();
        blackjackGame.handOutInitialCards();
        outputView.printInitialCards(blackjackGame);

        playPlayersTurn(blackjackGame);
        playDealerTurn(blackjackGame);

        printResults(blackjackGame);
    }

    private BlackjackGame createBlackjackGame() {
        Players players = retryOnInvalidUserInput(this::requestPlayers);
        return BlackjackGame.from(players, DeckFactory.getShuffledDeck());
    }

    private PlayerNames requestPlayerNames() {
        List<String> playerNamesUserInput = inputView.requestPlayerNames();
        return PlayerNames.from(playerNamesUserInput);
    }

    private Players requestPlayers() {
        List<Player> players = new ArrayList<>();
        PlayerNames playerNames = retryOnInvalidUserInput(this::requestPlayerNames);
        for (ParticipantName playerName : playerNames.getNames()) {
            String name = playerName.getName();
            BetAmount betAmount = retryOnInvalidUserInput(() -> requestBetAmount(name));
            players.add(Player.of(playerName, betAmount));
        }

        return new Players(players);
    }

    private BetAmount requestBetAmount(String name) {
        String betAmountUserInput = inputView.requestBetAmount(name);
        return BetAmount.from(betAmountUserInput);
    }

    private void playPlayersTurn(BlackjackGame blackjackGame) {
        Players players = blackjackGame.getPlayers();

        for (Player player : players.getPlayers()) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Player player) {
        BlackjackAction blackjackAction;

        do {
            blackjackAction = requestAction(player.getName());
            blackjackGame.playByAction(player, blackjackAction);
            outputView.printParticipantCards(player);
        } while (blackjackGame.isAbleToContinue(player, blackjackAction));

        if (player.isBusted()) {
            outputView.printBusted(player.getName());
        }
    }

    private BlackjackAction requestAction(String playerName) {
        return retryOnInvalidUserInput(() -> {
                    String commandValue = inputView.requestMoreCard(playerName);
                    return BlackjackAction.from(commandValue);
                }
        );
    }

    private void playDealerTurn(BlackjackGame blackjackGame) {
        Participant dealer = blackjackGame.getDealer();
        blackjackGame.handOutAdditionalCardToDealer();

        int hitCardCount = blackjackGame.getParticipantHitCardCount(dealer);
        outputView.printDealerHitCount(hitCardCount);
    }

    private void printResults(BlackjackGame blackjackGame) {
        TotalProfit totalProfit = TotalProfit.from(blackjackGame);

        outputView.printCardsWithScore(blackjackGame);
        outputView.printFinalResult(blackjackGame.getDealer(), totalProfit);
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
