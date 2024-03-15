package controller;

import domain.Betting;
import domain.Referee;
import domain.PlayerResult;
import domain.card.Card;
import domain.deck.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import dto.DealerProfit;
import dto.PlayerProfits;
import dto.TotalResult;
import strategy.RandomShuffleStrategy;
import view.InputView;
import view.OutputView;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    public void play() {
        Players players = readPlayers();
        Betting betting = readBetting(players);
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new RandomShuffleStrategy());
        prepareCards(deck, dealer, players);
        OutputView.printInitialCardsMessage(dealer, players);
        handOutCard(deck, dealer, players);
        OutputView.printCardsAndResult(dealer, players);
        TotalResult totalResult = findTotalResult(dealer, players, betting);
        OutputView.printTotalResult(totalResult);
    }

    private Players readPlayers() {
        List<String> names = InputView.readPlayerNames();
        return new Players(names);
    }

    private Betting readBetting(final Players players) {
        Map<Player, String> betting = new LinkedHashMap<>();
        for (final Player player : players.getPlayers()) {
            String betMoney = InputView.readBetting(player);
            betting.put(player,betMoney);
        }
        return new Betting(betting);
    }

    private void prepareCards(final Deck deck, final Dealer dealer, final Players players) {
        dealer.receive(drawTwoCards(deck));
        for (final Player player : players.getPlayers()) {
            player.receive(drawTwoCards(deck));
        }
    }

    private List<Card> drawTwoCards(final Deck deck) {
        return Stream.generate(deck::draw)
                .limit(INITIAL_CARD_COUNT)
                .toList();
    }

    private void handOutCard(final Deck deck, final Dealer dealer, final Players players) {
        askPlayersHit(deck, players);
        askDealerHit(deck, dealer);
    }

    private void askPlayersHit(final Deck deck, final Players players) {
        for (Player player : players.getPlayers()) {
            askSelection(deck, player);
        }
    }

    private void askSelection(final Deck deck, final Player player) {
        while (!player.isBust() && isRetry(deck, player)) {
            OutputView.printAllCards(player);
        }
    }

    private boolean isRetry(final Deck deck, final Player player) {
        boolean retry = InputView.readSelectionOf(player);
        if (!retry) {
            return false;
        }
        return takeTurn(deck, player);
    }

    private void askDealerHit(final Deck deck, final Dealer dealer) {
        while (takeTurn(deck, dealer)) {
            OutputView.printDealerHit(dealer);
        }
    }

    private boolean takeTurn(final Deck deck, final Gamer gamer) {
        if (!gamer.shouldStay()) {
            gamer.hit(deck.draw());
            return true;
        }
        return false;
    }

    private TotalResult findTotalResult(final Dealer dealer, final Players players, final Betting betting) {
        PlayerProfits playerProfits = new PlayerProfits();
        DealerProfit dealerProfit = new DealerProfit();
        for (Player player : players.getPlayers()) {
            PlayerResult playerResult = Referee.judgePlayer(dealer, player);
            BigDecimal playerProfit = betting.calculateProfit(player,playerResult);
            dealerProfit = dealerProfit.accumulate(playerProfit);
            playerProfits.addResult(player, playerProfit);
        }
        return new TotalResult(dealerProfit, playerProfits);
    }
}
