package blackjack.controller;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.player.isHit;
import blackjack.model.result.BettingBoard;
import blackjack.model.result.BettingMoney;
import blackjack.model.result.MatchResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
import blackjack.view.dto.PlayerProfit;

import java.util.*;
import java.util.function.Supplier;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardGenerator cardGenerator = new RandomCardGenerator();
        Players players = retryOnException(() -> preparePlayers(cardGenerator));
        BettingBoard bettingBoard = prepareBettingBoard(players);
        Dealer dealer = new Dealer(cardGenerator);
        outputView.printDealingResult(players, dealer);

        play(players, dealer, cardGenerator);
        end(players, dealer, bettingBoard);
    }

    private Players preparePlayers(final CardGenerator cardGenerator) {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames, cardGenerator);
    }

    private BettingBoard prepareBettingBoard(final Players players) {
        Map<Player, BettingMoney> board = new HashMap<>();
        for (Player player : players.getPlayers()) {
            int bettingMoney = retryOnException(() -> inputView.askBettingMoney(player.getName()));
            board.put(player, new BettingMoney(bettingMoney));
        }
        return new BettingBoard(board);
    }

    private void play(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        for (Player player : players.getPlayers()) {
            doPlayerActionUntilEnd(player, cardGenerator);
        }
        dealer.hitUntilEnd(cardGenerator);
        outputView.printDealerActionResult(dealer);
    }

    private void doPlayerActionUntilEnd(final Player player, final CardGenerator cardGenerator) {
        boolean isContinue = player.canHit();
        while (isContinue) {
            isContinue = doPlayerAction(player, cardGenerator);
        }
    }

    private boolean doPlayerAction(final Player player, final CardGenerator cardGenerator) {
        isHit isHit = retryOnException(() -> askPlayerHitTry(player.getName()));
        if (isHit.isAsked()) {
            player.hit(cardGenerator);
            outputView.printPlayerActionResult(player);
        }
        return isHit.canContinue(player);
    }

    private isHit askPlayerHitTry(final String playerName) {
        boolean askContinuance = inputView.askHitOrStandCommand(playerName);
        return isHit.from(askContinuance);
    }

    private void end(final Players players, final Dealer dealer, final BettingBoard bettingBoard) {
        showCardOutcome(players, dealer);
        showMatchResult(players, dealer, bettingBoard);
    }

    private void showCardOutcome(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.of(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printDealerFinalCards(dealerFinalCardsOutcome);
        outputView.printPlayersFinalCards(playerFinalCardsOutcomes);
    }

    private void showMatchResult(final Players players, final Dealer dealer, final BettingBoard bettingBoard) {
        List<PlayerProfit> playerProfits = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            MatchResult matchResult = dealer.determinePlayerMatchResult(player);
            BettingMoney profit = bettingBoard.determineProfit(player, matchResult);
            playerProfits.add(new PlayerProfit(player.getName(), profit.getAmount()));
        }
        outputView.printFinalProfits(playerProfits);
    }

    public <T> T retryOnException(final Supplier<T> retryOperation) {
        boolean retry = true;
        T result = null;
        while (retry) {
            result = tryOperation(retryOperation);
            retry = Objects.isNull(result);
        }
        return result;
    }

    private <T> T tryOperation(final Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return null;
        }
    }
}
