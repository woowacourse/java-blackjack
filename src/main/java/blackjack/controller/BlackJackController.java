package blackjack.controller;

import blackjack.domain.BlackJackDeckGenerator;
import blackjack.domain.BlackJackGame;
import blackjack.domain.HitCommand;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.util.InputHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        final List<String> playerNames = enrollPlayerNames();
        startGame(playerNames);
        hitOrStayForAvailablePlayers();
        hitUntilDealerAvailable();
        endGame();
    }

    private List<String> enrollPlayerNames() {
        return InputHandler.retryForIllegalArgument(InputView::askPlayerNames, this::initializeGame,
                OutputView::showInputErrorMessage);
    }

    private void initializeGame(final List<String> playerNames) {
        blackJackGame = new BlackJackGame(new BlackJackDeckGenerator(), new Dealer(),
                createPlayers(playerNames));
    }

    private List<Player> createPlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(name, enrollBettingMoneyFor(name)))
                .collect(Collectors.toList());
    }

    private BettingMoney enrollBettingMoneyFor(final String playerName) {
        final BigDecimal amount = InputHandler.retryForIllegalArgument(playerName, InputView::askBettingAmount,
                OutputView::showInputErrorMessage);
        return new BettingMoney(amount);
    }

    private void startGame(final List<String> playerNames) {
        blackJackGame.handOut();
        OutputView.showOpenCards(Dealer.NAME, playerNames, blackJackGame.openHandStatuses());
    }

    private void hitOrStayForAvailablePlayers() {
        final List<Player> hitAblePlayers = blackJackGame.getHitAblePlayers();
        hitAblePlayers.forEach(this::repeatHitOrStayUntilPlayerWants);
    }

    private void repeatHitOrStayUntilPlayerWants(final Player player) {
        HitCommand hitCommand = refreshHitCommand(player);
        while (hitCommand == HitCommand.YES) {
            blackJackGame.hit(player);
            OutputView.showPlayerCard(player.toHandStatus());
            hitCommand = refreshHitCommand(player);
        }
    }

    private HitCommand refreshHitCommand(final Player player) {
        if (player.isHitAble()) {
            return InputHandler.retryForIllegalArgument(player.getName(), InputView::askToHit,
                    OutputView::showInputErrorMessage);
        }
        return HitCommand.NO;
    }

    private void hitUntilDealerAvailable() {
        final int hitCount = blackJackGame.hitOrStayForDealer();
        OutputView.showDealerHitResult(Dealer.NAME, hitCount);
    }

    private void endGame() {
        OutputView.showHandStatuses(blackJackGame.openHandResults());
        OutputView.showTotalProfitResult(blackJackGame.computeTotalProfitResult());
    }
}
