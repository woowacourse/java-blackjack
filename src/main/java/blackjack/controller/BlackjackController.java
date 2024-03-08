package blackjack.controller;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.referee.Referee;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
import blackjack.view.dto.PlayerOutcome;

import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardGenerator cardGenerator = new RandomCardGenerator();
        Players players = preparePlayers(cardGenerator);
        Dealer dealer = new Dealer(cardGenerator);
        outputView.printDealingResult(players, dealer);
        play(cardGenerator, players, dealer);
        end(players, dealer);
    }

    private Players preparePlayers(final CardGenerator cardGenerator) {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames, cardGenerator);
    }

    private void play(final CardGenerator cardGenerator, final Players players, final Dealer dealer) {
        List<Player> playersInAction = players.getPlayers();
        for (Player player : playersInAction) {
            doPlayerActionUtilEnd(player, cardGenerator);
        }
        dealer.doAction(cardGenerator);
        outputView.printDealerActionResult(dealer);
    }

    private void doPlayerActionUtilEnd(final Player player, final CardGenerator cardGenerator) {
        boolean isContinue = true;
        while (isContinue) {
            isContinue = doPlayerAction(player, cardGenerator);
        }
    }

    private boolean doPlayerAction(final Player player, final CardGenerator cardGenerator) {
        boolean command = inputView.askHitOrStandCommand(player.getName());
        if (command) {
            player.hit(cardGenerator);
            outputView.printPlayerActionResult(player);
        }
        return player.canHit() && command;
    }

    private void end(final Players players, final Dealer dealer) {
        showCardOutcome(players, dealer);
        showGameOutcome(players, dealer);
    }

    private void showCardOutcome(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.of(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printDealerFinalCards(dealerFinalCardsOutcome);
        outputView.printPlayersFinalCards(playerFinalCardsOutcomes);
    }

    private void showGameOutcome(final Players players, final Dealer dealer) {
        Referee referee = new Referee(dealer);
        List<PlayerOutcome> outcomes = referee.determinePlayersOutcome(players);
        outputView.printFinalOutcome(outcomes);
    }
}
