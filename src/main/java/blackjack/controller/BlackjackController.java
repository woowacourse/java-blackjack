package blackjack.controller;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.game.Game;
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
        Game game = prepareGame();
        List<Player> playersInAction = players.getPlayers();
        for (Player player : playersInAction) {
            while (player.canHit()) {
                boolean command = inputView.askHitOrStandCommand(player.getName());
                if (command) {
                    player.hit(cardGenerator);
                    outputView.printPlayerActionResult(player);
                } else {
                    outputView.printPlayerActionResult(player);
                    break;
                }
            }
        }
        dealer.doAction(cardGenerator);
        outputView.printDealerActionResult(dealer);

        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.of(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printFinalCards(dealerFinalCardsOutcome, playerFinalCardsOutcomes);

        Referee referee = new Referee();
        List<PlayerOutcome> outcomes = playersInAction.stream()
                .map(player -> referee.determinePlayerOutcome(player, dealer))
                .toList();

        outputView.printFinalOutcome(outcomes);
    }

    private Game prepareGame() {
        CardGenerator cardGenerator = new RandomCardGenerator();
        Players players = preparePlayers(cardGenerator);
        Dealer dealer = new Dealer(cardGenerator);
        outputView.printDealingResult(players, dealer);
        return new Game(players, dealer, cardGenerator);
    }

    private Players preparePlayers(final CardGenerator cardGenerator) {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames, cardGenerator);
    }
}
