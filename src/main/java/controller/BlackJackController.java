package controller;

import domain.Card;
import domain.CardShuffler;
import domain.CardsInitializer;
import domain.Dealer;
import domain.Deck;
import domain.MatchResult;
import domain.Player;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
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
        Deck deck = prepareDeck();

        List<Player> players = setPlayers();
        Dealer dealer = new Dealer();

        prepareGame(dealer, players, deck);
        outputView.printInitialCards(dealer, players);

        processGame(players, deck);

        dealerHit(dealer, deck);

        outputView.printCardResult(dealer, players);

        showMatchResult(players, dealer);
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

    private void processGame(List<Player> players, Deck deck) {
        for (Player player : players) {
            selectChoice(player, deck);
        }
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

    private void showMatchResult(List<Player> players, Dealer dealer) {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);
        Map<Player, MatchResult> playerResult = new HashMap<>();

        for (Player player : players) {
            MatchResult result = dealer.getMatchResult(player);
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, result.reverse());
        }

        outputView.printMatchResult(dealerResult, playerResult);
    }
}
