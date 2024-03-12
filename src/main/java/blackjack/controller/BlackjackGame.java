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

public class BlackjackGame {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        CardGenerator cardGenerator = new RandomCardGenerator();
        Players players = retryOnException(() -> preparePlayers(cardGenerator));
        Dealer dealer = new Dealer(cardGenerator);

        dealCards(players, dealer, cardGenerator);
        drawCards(players, dealer, cardGenerator);
        showResult(players, dealer);
    }

    private Players preparePlayers(final CardGenerator cardGenerator) {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames, cardGenerator);
    }

    private void dealCards(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        // TODO: deal 로직 분리
        outputView.printDealingCards(players, dealer);
    }

    private void drawCards(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        if (isGameEnd(dealer, players)) {
            return;
        }
        for (Player player : players.getPlayers()) {
            drawPlayerCards(player, cardGenerator);
        }
        drawDealerCards(dealer, cardGenerator);
    }

    private boolean isGameEnd(final Dealer dealer, final Players players) {
        return dealer.isBlackJack(); // TODO: players 전부 블랙잭인지도 확인
    }

    private void drawPlayerCards(final Player player, final CardGenerator cardGenerator) {
        boolean isContinue = player.canDraw();
        while (isContinue) {
            isContinue = drawPlayerCard(player, cardGenerator);
        }
    }

    private boolean drawPlayerCard(final Player player, final CardGenerator cardGenerator) {
        Command command = retryOnException(() -> inputView.askPlayerDrawOrStandCommand(player.getName()));
        if (command.isDraw()) {
            player.draw(cardGenerator);
            outputView.printPlayerDrawingCards(player);
        }
        return player.canDraw() && command.isDraw();
    }

    private void drawDealerCards(final Dealer dealer, final CardGenerator cardGenerator) {
        dealer.drawUntilEnd(cardGenerator);
        outputView.printDealerDrawingCards(dealer);
    }

    private void showResult(final Players players, final Dealer dealer) {
        showCardsOutcome(players, dealer);
        showMatchResult(players, dealer);
    }

    private void showCardsOutcome(final Players players, final Dealer dealer) {
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
