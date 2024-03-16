package blackjack.controller;

import blackjack.model.betting.Betting;
import blackjack.model.betting.BettingMoney;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerBettingProfitOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
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
        Betting betting = createBetting(players);

        dealCards(players, dealer, cardGenerator);
        drawCards(players, dealer, cardGenerator);
        showResult(players, dealer, betting);
    }

    private Players createPlayers() {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames);
    }

    private Betting createBetting(Players players) {
        Betting betting = new Betting();
        for (String playerName : players.getNames()) {
            BettingMoney bettingMoney = retryOnException(() -> inputView.askBettingMoneyToPlayer(playerName));
            betting.addPlayerBettingMoney(playerName, bettingMoney);
        }
        return betting;
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

    private void showResult(final Players players, final Dealer dealer, final Betting betting) {
        showFinalCardsOutcome(players, dealer);
        showBettingProfitOutcome(players, dealer, betting);
    }

    private void showFinalCardsOutcome(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.from(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printDealerFinalCards(dealerFinalCardsOutcome); // TODO: 합치기
        outputView.printPlayersFinalCards(playerFinalCardsOutcomes);
    }

    private void showBettingProfitOutcome(final Players players, final Dealer dealer, final Betting betting) {
        List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes = players.calculateBettingProfits(betting, dealer);
        int dealerBettingProfit = dealer.calculateBettingProfit(playerBettingProfitOutcomes);
        outputView.printBettingProfit(dealerBettingProfit, playerBettingProfitOutcomes);
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
