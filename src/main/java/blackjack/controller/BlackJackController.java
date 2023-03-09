package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = generatePlayers();

        BlackJackGame blackJackGame = BlackJackGame.create(players);

        start(blackJackGame);

        play(blackJackGame);

        showResult(blackJackGame);
    }


    private void start(BlackJackGame blackJackGame) {
        blackJackGame.setUp();

        outputView.showInitStatus(blackJackGame.getPlayers());
        outputView.showDealerFirstCard(blackJackGame.getDealer().getFirstCard());
        outputView.showPlayers(blackJackGame.getPlayers());
    }

    private void play(BlackJackGame blackJackGame) {
        passExtraCardToPlayers(blackJackGame);
        passExtraCardToDealer(blackJackGame);
    }

    private void showResult(BlackJackGame blackJackGame) {
        outputView.showTotalScore(blackJackGame.getDealer(), blackJackGame.getPlayers());
        Map<Player, Result> result = blackJackGame.calculateResult();
        outputView.showFinalResult(result, blackJackGame.getPlayers());
    }

    private void passExtraCardToPlayers(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            addExtraCard(blackJackGame, player);
        }
    }

    private void addExtraCard(BlackJackGame blackJackGame, Player player) {
        while (player.canReceive() && hasIntention(player.getName())) {
            blackJackGame.passCardTo(player);
            outputView.showPlayer(player);
        }
    }

    private void passExtraCardToDealer(BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();
        if (dealer.canReceive()) {
            blackJackGame.passCardTo(dealer);
            outputView.showDealerDrawPossible();
            return;
        }
        outputView.showDealerDrawImpossible();
    }

    private boolean hasIntention(String name) {
        return inputView.readIntention(name);
    }

    private Players generatePlayers() {
        try {
            List<String> names = inputView.readNames();
            return Players.create(names);
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            return generatePlayers();
        }
    }
}
