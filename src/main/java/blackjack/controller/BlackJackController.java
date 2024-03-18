package blackjack.controller;

import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.RandomCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Name;
import blackjack.model.player.PlayerAction;
import blackjack.model.player.PlayerActionExecutor;
import blackjack.model.player.Players;
import blackjack.model.result.BettingBoard;
import blackjack.model.result.BettingMoney;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.ExecutingPlayer;
import blackjack.view.dto.PlayerEarning;
import blackjack.view.dto.PlayerFinalCardsOutcome;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        printDealingResult(players, dealer);

        play(players, dealer, cardGenerator);
        end(players, dealer, bettingBoard);
    }

    private Players preparePlayers(final CardGenerator cardGenerator) {
        List<String> playerNames = inputView.askPlayerNames();
        return new Players(playerNames, cardGenerator);
    }

    private BettingBoard prepareBettingBoard(final Players players) {
        return players.getNames().stream()
                .map(name -> retryOnException(() -> askBettingMoney(name)))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        bettingMonies -> new BettingBoard(players.getNames(), bettingMonies)));
    }

    private void printDealingResult(final Players players, final Dealer dealer) {
        List<ExecutingPlayer> executingPlayers = players.captureExecutingPlayer();
        String dealerCard = dealer.getFirstCard().toString();
        outputView.printDealingResult(executingPlayers, dealerCard);
    }

    private BettingMoney askBettingMoney(final Name playerName) {
        int bettingMoney = inputView.askBettingMoney(playerName.toString());
        return new BettingMoney(bettingMoney);
    }

    private void play(final Players players, final Dealer dealer, final CardGenerator cardGenerator) {
        doPlayerAction(players, cardGenerator);
        dealer.hitUntilEnd(cardGenerator);
        outputView.printDealerActionResult(dealer.getActionCount());
    }

    private void doPlayerAction(final Players players, final CardGenerator cardGenerator) {
        PlayerActionExecutor playerActionExecutor = new PlayerActionExecutor(players, cardGenerator);
        while (!playerActionExecutor.isFinished()) {
            ExecutingPlayer player = playerActionExecutor.getExecutingPlayer();
            PlayerAction playerAction = retryOnException(() -> askPlayerHitTry(player));
            playerActionExecutor.execute(playerAction);
            printPlayerActionResult(playerActionExecutor);
        }
    }

    private void printPlayerActionResult(final PlayerActionExecutor playerActionExecutor) {
        ExecutingPlayer player = playerActionExecutor.getExecutingPlayer();
        outputView.printPlayerActionResult(player);
    }

    private PlayerAction askPlayerHitTry(final ExecutingPlayer player) {
        boolean askContinuance = inputView.askHitOrStandCommand(player.name());
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
