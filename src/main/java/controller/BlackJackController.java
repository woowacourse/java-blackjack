package controller;

import domain.BetSystem;
import domain.Card;
import domain.CardShuffler;
import domain.CardsInitializer;
import domain.Dealer;
import domain.Deck;
import domain.Gamer;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final String YES_SIGN = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BetSystem betSystem = new BetSystem();
        Deck deck = prepareDeck();
        List<Player> players = setPlayers();
        Dealer dealer = new Dealer();

        playerBetting(players, betSystem);

        prepareGame(dealer, players, deck);
        outputView.printInitialCards(dealer, players);

        if (isPrepareBlackjack(dealer, players, betSystem)) {
            return;
        }

        processGame(players, dealer, deck);

        outputView.printCardResult(dealer, players);
        profitResult(betSystem, dealer, players);
    }

    private void profitResult(final BetSystem betSystem, final Dealer dealer, final List<Player> players) {
        Map<Gamer, Integer> gamerIntegerMap = betSystem.calculateProfit(dealer, players);
        outputView.printProfitResult(gamerIntegerMap);
    }

    private boolean isPrepareBlackjack(final Dealer dealer, final List<Player> players, final BetSystem betSystem) {
        if (isPrepareCardsBlackjack(dealer, players)) {
            Map<Gamer, Integer> gamerIntegerMap = betSystem.calculateProfit(dealer, players);
            outputView.printCardResult(dealer, players);
            outputView.printProfitResult(gamerIntegerMap);
            return true;
        }
        return false;
    }

    private boolean isPrepareCardsBlackjack(final Dealer dealer, final List<Player> players) {
        if (dealer.isBlackjack()) {
            return true;
        }
        for (Player player : players) {
            if (player.isBlackjack()) {
                return true;
            }
        }
        return false;
    }

    private void playerBetting(final List<Player> players, final BetSystem betSystem) {
        for (Player player : players) {
            int betAmount = inputView.readBetAmount(player);
            betSystem.betting(player, betAmount);
        }
    }

    private Deck prepareDeck() {
        List<Card> cards = new CardsInitializer(new CardShuffler()).initialize();
        return Deck.from(cards);
    }

    private List<Player> setPlayers() {
        List<String> names = inputView.readNames();
        return createPlayers(names);
    }

    private List<Player> createPlayers(List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            players.add(player);
        }
        return players;
    }

    private void prepareGame(Dealer dealer, List<Player> players, Deck deck) {
        dealer.prepareGame(deck);
        players.forEach(player -> player.prepareGame(deck));
    }

    private void processGame(List<Player> players, final Dealer dealer, Deck deck) {
        for (Player player : players) {
            selectChoice(player, deck);
        }
        dealerHit(dealer, deck);

    }

    private void dealerHit(Dealer dealer, Deck deck) {
        try {
            dealer.hit(deck);
            outputView.printDealerHitSuccess();
        } catch (IllegalStateException e) {
            outputView.printDealerNonHit();
        }
    }

    private void selectChoice(Player player, Deck deck) {
        while (canHit(player)) {
            player.hit(deck);
            outputView.printCards(player);
        }
        if (!player.isBust()) {
            outputView.printCards(player);
        }
    }

    private boolean canHit(Player player) {
        return !player.isBust() && YES_SIGN.equals(getYesOrNo(player));
    }

    private String getYesOrNo(Player player) {
        return inputView.readYesOrNo(player);
    }

}
