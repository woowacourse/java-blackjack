package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private static final String CARD_RECEIVE_INTENTION = "y";
    private static final int EXTRA_CARD_COUNT = 1;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = generatePlayers();

        BlackJackGame blackJackGame = BlackJackGame.create(players);
        blackJackGame.setUp();

        showInitialStatus(blackJackGame);

        passExtraCard(blackJackGame);

        showFinalResult(blackJackGame);
    }


    private void passExtraCard(BlackJackGame blackJackGame) {
        passExtraCardToPlayers(blackJackGame);
        passExtraCardToDealer(blackJackGame);
    }

    private void showInitialStatus(BlackJackGame blackJackGame) {
        outputView.showInitStatus(blackJackGame.getPlayers());
        outputView.showDealerFirstCard(blackJackGame.getDealer().getFirst());
        outputView.showPlayers(blackJackGame.getPlayers());
    }

    private void showFinalResult(BlackJackGame blackJackGame) {
        outputView.showTotalScore(blackJackGame.getDealer(), blackJackGame.getPlayers());
        Map<Player, Result> result = blackJackGame.calculateResult();
        outputView.showFinalResult(result, blackJackGame.getPlayers());
    }

    private void passExtraCardToDealer(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        if (dealer.canReceive()) {
            blackJackGame.drawCard(dealer, EXTRA_CARD_COUNT);
            outputView.showDealerDrawPossible();
            return;
        }
        outputView.showDealerDrawImpossible();
    }

    private void passExtraCardToPlayers(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            addExtraCard(blackJackGame, player);
        }
    }

    private void addExtraCard(BlackJackGame blackJackGame, Player player) {
        while (player.canReceive() && hasIntention(inputView.readIntention(player.getName()))) {
            blackJackGame.drawCard(player, EXTRA_CARD_COUNT);
            outputView.showPlayer(player);
        }
    }

    private boolean hasIntention(String intention) {
        return intention.equals(CARD_RECEIVE_INTENTION);
    }

    private Players generatePlayers() {
        try {
            List<String> names = inputView.readNames();
            Players players = Players.create(names);
            return players;
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            return generatePlayers();
        }
    }
}
