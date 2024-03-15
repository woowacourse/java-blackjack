package blackjack.controller;

import blackjack.model.betting.Money;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
import blackjack.view.dto.PlayerMatchResultOutcome;
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
        Players players = retryOnException(this::createPlayers);
        Dealer dealer = new Dealer();
        CardGenerator cardGenerator = new RandomCardGenerator();
        createBetting(players); // TODO: betting 반환하게 수정

        dealCards(players, dealer, cardGenerator);
        drawCards(players, dealer, cardGenerator);
        showResult(players, dealer);
    }

    private Players createPlayers() {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames);
    }

    private void createBetting(Players players) {
        for (String playerName : players.getNames()) {
            Money money = retryOnException(() -> inputView.askBettingMoneyToPlayer(playerName));
        }
    }

    private void dealCards(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        players.dealCards(cardGenerator);
        dealer.dealCards(cardGenerator);
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
        return dealer.isBlackjack() || players.isAllBlackJack();
    }

    private void drawPlayerCards(final Player player, final CardGenerator cardGenerator) {
        boolean isContinue = player.canDrawCard();
        while (isContinue) {
            isContinue = drawPlayerCard(player, cardGenerator);
        }
    }

    private boolean drawPlayerCard(final Player player, final CardGenerator cardGenerator) {
        Command command = retryOnException(() -> inputView.askDrawOrStandCommandToPlayer(player.getName()));
        if (command.isDraw()) {
            player.drawCard(cardGenerator);
            outputView.printPlayerDrawingCards(player);
        }
        return player.canDrawCard() && command.isDraw();
    }

    private void drawDealerCards(final Dealer dealer, final CardGenerator cardGenerator) {
        dealer.drawCards(cardGenerator);
        outputView.printDealerDrawingCards(dealer);
    }

    private void showResult(final Players players, final Dealer dealer) {
        showCardsOutcome(players, dealer);
        showMatchResult(players, dealer);
    }

    private void showCardsOutcome(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.from(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printDealerFinalCards(dealerFinalCardsOutcome);
        outputView.printPlayersFinalCards(playerFinalCardsOutcomes);
    }

    private void showMatchResult(final Players players, final Dealer dealer) {
        List<PlayerMatchResultOutcome> playerMatchResultOutcomes = players.determineMatchResults(dealer);
        outputView.printMatchResult(playerMatchResultOutcomes);
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
