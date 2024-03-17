package controller;

import domain.BetAmounts;
import domain.BlackJackGame;
import domain.card.Deck;
import domain.card.ShuffledCardsGenerator;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.gamer.Players;
import handler.RetryHandler;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final RetryHandler retryHandler;

    public BlackJackController(final RetryHandler retryHandler) {
        this.retryHandler = retryHandler;
    }

    public void start() {
        Dealer dealer = new Dealer();
        Players players = retryHandler.retry(this::createPlayers);
        BlackJackGame blackJackGame = createBlackJackGame();
        startBet(players, blackJackGame);
        blackJackGame.prepareCards(dealer, players);
        OutputView.printInitialCardsMessage(dealer, players);
        handOutCard(blackJackGame, dealer, players);
        printFinalGameResult(blackJackGame, dealer, players);
    }

    private Players createPlayers() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(players);
    }

    private BlackJackGame createBlackJackGame() {
        ShuffledCardsGenerator shuffledCardsGenerator = new ShuffledCardsGenerator();
        Deck deck = new Deck(shuffledCardsGenerator.generate());
        BetAmounts betAmounts = new BetAmounts();
        return new BlackJackGame(deck, betAmounts);
    }

    private void startBet(final Players players, final BlackJackGame blackJackGame) {
        for (Player player : players.getPlayers()) {
            retryHandler.retry(() -> blackJackGame.bet(player, InputView.readBetAmount(player)));
        }
    }

    private void handOutCard(final BlackJackGame blackJackGame, final Dealer dealer, final Players players) {
        askPlayersHit(blackJackGame, players);
        askDealerHit(blackJackGame, dealer);
        OutputView.printCardsAndResult(dealer, players);
    }

    private void askPlayersHit(final BlackJackGame blackJackGame, final Players players) {
        for (Player player : players.getPlayers()) {
            askSelection(blackJackGame, player);
        }
    }

    private void askSelection(final BlackJackGame blackJackGame, final Player player) {
        while (player.canHit() && isRetryCommand(player)) {
            blackJackGame.giveCard(player);
            OutputView.printAllCards(player);
        }
    }

    private boolean isRetryCommand(final Player player) {
        return retryHandler.retry(() -> InputView.readSelectionOf(player));
    }

    private void askDealerHit(final BlackJackGame blackJackGame, final Dealer dealer) {
        OutputView.printNewLine();
        while (dealer.canHit()) {
            blackJackGame.giveCard(dealer);
            OutputView.printDealerHit(dealer);
        }
        OutputView.printNewLine();
    }

    private void printFinalGameResult(final BlackJackGame blackJackGame, final Dealer dealer, final Players players) {
        Map<Player, Integer> playersResult = blackJackGame.createPlayersResult(dealer, players);
        int dealerProfit = blackJackGame.calculateDealerProfit(dealer, players);
        OutputView.printFinalGameResult(dealerProfit, playersResult);
    }
}
