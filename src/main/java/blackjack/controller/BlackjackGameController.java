package blackjack.controller;

import blackjack.domain.betting.Bettings;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeckGenerator;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;
import blackjack.dto.gamer.PlayerInfo;
import blackjack.dto.gamer.PlayerInfos;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.command.Command;

import java.util.HashMap;
import java.util.Map;

public class BlackjackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Players players = Players.fromNames(inputView.readPlayerNames());
        Dealer dealer = new Dealer();
        Deck deck = createDeck();

        Bettings bettings = betPlayers(players, dealer);

        dealInitCards(deck, dealer, players);
        receiveAdditionalCard(deck, dealer, players);

        GameResults gameResults = judgeGameResult(players, dealer);
        Map<Gamer, Integer> gamerProfits = calculateProfits(players, gameResults, bettings, dealer);

        printGameResult(dealer, players, gamerProfits);
    }

    private Bettings betPlayers(Players players, Dealer dealer) {
        Bettings bettings = new Bettings();
        bettings.add(dealer, 0);

        for (Player player : players.getPlayers()) {
            int money = inputView.readPlayerBettingMoney(player.getName());
            bettings.add(player, money);
        }

        return bettings;
    }

    private Deck createDeck() {
        ShuffledDeckGenerator deckGenerator = ShuffledDeckGenerator.getInstance();
        return deckGenerator.generate();
    }

    private void dealInitCards(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            player.receiveInitCards(deck.drawInitCards());
        }
        dealer.receiveInitCards(deck.drawInitCards());

        outputView.printInitCardStatus(dealer.getFirstCard(), PlayerInfos.from(players));
    }

    private void receiveAdditionalCard(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            receivePlayerAdditionalCard(deck, player);
        }
        receiveDealerAdditionalCard(deck, dealer);
    }

    private void receivePlayerAdditionalCard(final Deck deck, final Player player) {
        while (!player.isBust() && isPlayerInputHit(player)) {
            player.receiveCard(deck.drawCard());
            outputView.printCardsStatus(PlayerInfo.from(player));
        }
    }

    private boolean isPlayerInputHit(final Player player) {
        return inputView.readHitOrStand(player).equals(Command.YES);
    }

    private void receiveDealerAdditionalCard(final Deck deck, final Dealer dealer) {
        while (dealer.hasHitScore()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerHitMessage();
        }
    }

    private GameResults judgeGameResult(Players players, Dealer dealer) {
        GameResults gameResults = new GameResults();
        for (final Player player : players.getPlayers()) {
            gameResults.add(player, GameResult.ofPlayer(player, dealer));
        }

        return gameResults;
    }

    private Map<Gamer, Integer> calculateProfits(Players players, GameResults gameResults, Bettings bettings, Dealer dealer) {
        Map<Gamer, Integer> gamerProfits = new HashMap<>();
        for (final Player player : players.getPlayers()) {
            int playerProfit = gameResults.calculatePlayerProfit(player, bettings);
            gamerProfits.put(player, playerProfit);
        }
        gamerProfits.put(dealer, gameResults.calculateDealerProfit(bettings));

        return gamerProfits;
    }

    private void printGameResult(Dealer dealer, Players players, Map<Gamer, Integer> gamerProfits) {
        outputView.printTotalCardsStatus(dealer, players);
        outputView.printTotalProfit(dealer, players, gamerProfits);
    }
}
