package blackjack.controller;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.PlayerAction;
import blackjack.model.player.Players;
import blackjack.model.result.BettingBoard;
import blackjack.model.result.BettingMoney;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerEarning;
import blackjack.view.dto.PlayerFinalCardsOutcome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        Map<String, BettingMoney> board = new HashMap<>();
        for (String playerName : players.getNames()) {
            BettingMoney bettingMoney = retryOnException(() -> askBettingMoney(playerName));
            board.put(playerName, bettingMoney);
        }
        return new BettingBoard(board);
    }

    private BettingMoney askBettingMoney(final String playerName) {
        int bettingMoney = inputView.askBettingMoney(playerName);
        return new BettingMoney(bettingMoney);
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
        PlayerAction PlayerAction = retryOnException(() -> askPlayerHitTry(player.getName()));
        if (PlayerAction.isHit()) {
            player.hit(cardGenerator);
            outputView.printPlayerActionResult(player);
        }
        return PlayerAction.canContinue(player);
    }

    private PlayerAction askPlayerHitTry(final String playerName) {
        boolean askContinuance = inputView.askHitOrStandCommand(playerName);
        return PlayerAction.from(askContinuance);
    }

    private void end(final Players players, final Dealer dealer, final BettingBoard bettingBoard) {
        showCardOutcome(players, dealer);
        showEarnings(players, dealer, bettingBoard);
    }

    private void showCardOutcome(final Players players, final Dealer dealer) {
        DealerFinalCardsOutcome dealerFinalCardsOutcome = DealerFinalCardsOutcome.of(dealer);
        List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes = players.captureFinalCardsOutcomes();
        outputView.printDealerFinalCards(dealerFinalCardsOutcome);
        outputView.printPlayersFinalCards(playerFinalCardsOutcomes);
    }

    private void showEarnings(final Players players, final Dealer dealer, final BettingBoard bettingBoard) {
        List<PlayerEarning> playerEarnings = players.determineEarnings(dealer, bettingBoard);
        outputView.printEarnings(playerEarnings);
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
