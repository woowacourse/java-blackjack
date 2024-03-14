package controller;

import domain.*;
import domain.card.Card;
import domain.deck.Deck;
import domain.gamer.*;
import dto.DealerResult;
import dto.PlayerResults;
import dto.TotalResult;
import strategy.RandomShuffleStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Stream;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;

    public void play() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new RandomShuffleStrategy());
        prepareCards(deck, dealer, players);
        OutputView.printInitialCardsMessage(dealer, players);
        handOutCard(deck, dealer, players);
        OutputView.printCardsAndResult(dealer, players);
        TotalResult totalResult = findTotalResult(dealer, players);
        OutputView.printFinalGameResult(totalResult);
    }

    private Players readPlayers() {
        List<String> names = InputView.readPlayerNames();
        return createPlayers(names);
    }

    private Players createPlayers(final List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        return new Players(players);
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

    private TotalResult findTotalResult(final Dealer dealer, final Players players) {
        PlayerResults playerResults = new PlayerResults();
        DealerResult dealerResult = new DealerResult();
        for (Player player : players.getPlayers()) {
            Result result = Referee.judge(dealer, player);
            dealerResult = dealerResult.addResult(result);
            playerResults.addResult(player, findPlayerResult(result));
        }
        return new TotalResult(dealerResult, playerResults);
    }

    private Result findPlayerResult(final Result result) {
        if (result.won()) {
            return Result.LOSE;
        }
        if (result.lost()) {
            return Result.WIN;
        }
        return Result.TIE;
    }
}
