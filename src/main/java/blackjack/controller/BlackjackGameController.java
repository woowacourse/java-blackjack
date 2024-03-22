package blackjack.controller;

import blackjack.domain.betting.Bettings;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.card.deck.ShuffledDeckCardGenerator;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;
import blackjack.domain.result.GamerProfits;
import blackjack.dto.gamer.PlayerInfo;
import blackjack.dto.gamer.PlayerInfos;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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
        Deck deck = new Deck(ShuffledDeckCardGenerator.getInstance());

        Bettings bettings = betPlayers(players, dealer);
        dealInitCards(deck, dealer, players);
        receiveAdditionalCard(deck, dealer, players);

        GameResults gameResults = judgeGameResult(players, dealer);
        GamerProfits gamerProfits = gameResults.calculateGamerProfits(players, dealer, bettings);
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
        while (!player.isBust() && inputView.readHitOrStand(player).isYes()) {
            player.receiveCard(deck.drawCard());
            outputView.printCardsStatus(PlayerInfo.from(player));
        }
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

    private void printGameResult(final Dealer dealer, final Players players, final GamerProfits gamerProfits) {
        outputView.printTotalCardsStatus(dealer, players);
        outputView.printTotalProfit(dealer, players, gamerProfits);
    }
}
