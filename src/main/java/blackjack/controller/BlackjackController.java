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
import blackjack.view.dto.PlayerMatchResult;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Game game = prepare();
        play(game);
        end(game);
    }

    private Game prepare() {
        CardGenerator cardGenerator = new RandomCardGenerator();
        Players players = retryOnException(() -> preparePlayers(cardGenerator));
        Dealer dealer = new Dealer(cardGenerator);
        outputView.printDealingResult(players, dealer);
        return new Game(players, dealer, cardGenerator);
    }

    private Players preparePlayers(final CardGenerator cardGenerator) {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames, cardGenerator);
    }

    private void play(final Game game) {
        if (canAction(game.dealer())) {
            return;
        }
        doAction(game.players(), game.dealer(), game.cardGenerator());
    }

    private boolean canAction(final Dealer dealer) {
        return dealer.isBlackJack();
    }

    private void doAction(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        for (Player player : players.getPlayers()) {
            doPlayerActionUtilEnd(player, cardGenerator);
        }
        doDealerAction(dealer, cardGenerator);
    }

    private void doPlayerActionUtilEnd(final Player player, final CardGenerator cardGenerator) {
        boolean isContinue = player.canHit();
        while (isContinue) {
            isContinue = doPlayerAction(player, cardGenerator);
        }
    }

    private boolean doPlayerAction(final Player player, final CardGenerator cardGenerator) {
        boolean command = retryOnException(() -> inputView.askHitOrStandCommand(player.getName()));
        if (command) {
            player.hit(cardGenerator);
            outputView.printPlayerActionResult(player);
        }
        return player.canHit() && command;
    }

    private void doDealerAction(Dealer dealer, CardGenerator cardGenerator) {
        dealer.hitUntilEnd(cardGenerator);
        outputView.printDealerActionResult(dealer);
    }

    private void end(final Game game) {
        Players players = game.players();
        Dealer dealer = game.dealer();
        showCardOutcome(players, dealer);
        showMatchResult(players, dealer);
    }

    private void showCardOutcome(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.of(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printDealerFinalCards(dealerFinalCardsOutcome);
        outputView.printPlayersFinalCards(playerFinalCardsOutcomes);
    }

    private void showMatchResult(final Players players, final Dealer dealer) {
        Referee referee = new Referee(players, dealer);
        List<PlayerMatchResult> playerMatchResults = referee.determinePlayersMatchResult();
        outputView.printMatchResult(playerMatchResults);
    }

    public <T> T retryOnException(final Supplier<T> retryOperation) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryOperation(retryOperation);
        }
        return result.get();
    }

    private <T> Optional<T> tryOperation(final Supplier<T> operation) {
        try {
            return Optional.of(operation.get());
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return Optional.empty();
        }
    }
}
