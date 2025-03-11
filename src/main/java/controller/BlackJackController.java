package controller;

import domain.Card;
import domain.CardsInitializer;
import domain.Dealer;
import domain.Deck;
import domain.Gamer;
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

        List<Gamer> players = setPlayers();
        Gamer dealer = new Dealer();

        prepareGame(dealer, players, deck);
        outputView.printInitialCards(dealer, players);

        processGame(players, deck);

        dealerHit(dealer, deck);

        outputView.printCardResult(dealer, players);

        showMatchResult(players, dealer);
    }

    private void dealerHit(Gamer dealer, Deck deck) {
        dealer.hit(deck);
        outputView.printDealerHitSuccess();
    }

    private Deck prepareDeck() {
        List<Card> cards = new CardsInitializer().initialize();
        return Deck.from(cards);
    }

    private List<Gamer> setPlayers() {
        List<String> names = inputView.readNames();
        return createPlayers(names);
    }

    private List<Gamer> createPlayers(List<String> names) {
        List<Gamer> players = new ArrayList<>();
        for (String name : names) {
            Gamer player = new Player(name);
            players.add(player);
        }
        return players;
    }

    private void prepareGame(Gamer dealer, List<Gamer> players, Deck deck) {
        dealer.prepareGame(deck);
        players.forEach(player -> player.prepareGame(deck));
    }

    private void processGame(List<Gamer> players, Deck deck) {
        for (Gamer player : players) {
            selectChoice(player, deck);
        }
    }

    private void selectChoice(Gamer player, Deck deck) {
        while (canHit(player)) {
            player.hit(deck);
            outputView.printCards(player);
        }
        if (!player.isBust()) {
            outputView.printCards(player);
        }
    }

    private boolean canHit(Gamer player) {
        return !player.isBust() && YES_SIGN.equals(getYesOrNo(player));
    }

    private String getYesOrNo(Gamer player) {
        return inputView.readYesOrNo(player);
    }

    private void showMatchResult(List<Gamer> players, Gamer dealer) {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);
        Map<Gamer, MatchResult> playerResult = new HashMap<>();

        for (Gamer player : players) {
            MatchResult result = MatchResult.judge(dealer.getHand(), player.getHand());
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, MatchResult.judge(player.getHand(), dealer.getHand()));
        }
        outputView.printMatchResult(dealerResult, playerResult);
    }
}
