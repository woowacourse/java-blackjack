package blackjack.controller;

import blackjack.dto.DealerFinalCardsOutcome;
import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.dto.PlayerCardsOutcome;
import blackjack.dto.PlayerFinalCardsOutcome;
import blackjack.model.betting.Betting;
import blackjack.model.betting.BettingMoney;
import blackjack.model.card.Card;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.PlayerName;
import blackjack.model.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.form.Command;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        showFinalCards(players, dealer);
        showBettingProfit(players, dealer, betting);
    }

    private Players createPlayers() {
        List<PlayerName> playerNames = inputView.askPlayerNames();
        return new Players(playerNames);
    }

    private Betting createBetting(final Players players) {
        Map<PlayerName, BettingMoney> playerBettingMoney = new HashMap<>();
        for (PlayerName playerName : players.getNames()) {
            BettingMoney bettingMoney = retryOnException(() -> inputView.askBettingMoneyToPlayer(playerName));
            playerBettingMoney.put(playerName, bettingMoney);
        }
        return new Betting(playerBettingMoney);
    }

    private void dealCards(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        players.dealCards(cardGenerator);
        dealer.dealCards(cardGenerator);
        showDealingCards(players, dealer);
    }

    private void showDealingCards(final Players players, final Dealer dealer) {
        List<PlayerName> playerNames = players.getNames();
        List<PlayerCardsOutcome> playerCardsOutcomes = players.getPlayers().stream()
                .map(PlayerCardsOutcome::from)
                .toList();
        Card dealerFirstCard = dealer.getFirstCard();
        outputView.printDealingCards(playerNames, playerCardsOutcomes, dealerFirstCard);
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
            outputView.printPlayerDrawingCards(PlayerCardsOutcome.from(player));
        }
        return player.canDrawCard() && command.isDraw();
    }

    private void drawDealerCards(final Dealer dealer, final CardGenerator cardGenerator) {
        dealer.drawCards(cardGenerator);
        outputView.printDealerDrawingCards(dealer.getDrawCount());
    }

    private void showFinalCards(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.from(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.getPlayers().stream()
                .map(PlayerFinalCardsOutcome::from)
                .toList();
        outputView.printFinalCards(dealerFinalCardsOutcome, playerFinalCardsOutcomes);
    }

    private void showBettingProfit(final Players players, final Dealer dealer, final Betting betting) {
        List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes = players.getPlayers().stream()
                .map(player -> PlayerBettingProfitOutcome.from(player, betting, dealer))
                .toList();
        int dealerBettingProfit = calculateDealerBettingProfit(playerBettingProfitOutcomes);
        outputView.printBettingProfit(dealerBettingProfit, playerBettingProfitOutcomes);
    }

    private int calculateDealerBettingProfit(final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        return playerBettingProfitOutcomes.stream()
                .mapToInt(PlayerBettingProfitOutcome::profit)
                .sum() * -1;
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
