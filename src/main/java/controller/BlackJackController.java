package controller;

import domain.CardsInitializer;
import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Result;
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
    private final CardsInitializer cardsInitializer;

    public BlackJackController(InputView inputView, OutputView outputView, CardsInitializer cardsInitializer) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardsInitializer = cardsInitializer;
    }

    public void run() {
        Deck deck = cardsInitializer.initialize();

        List<Player> players = setPlayers();
        Dealer dealer = new Dealer();

        prepareGame(dealer, players, deck);

        outputView.printInitialCards(dealer, players);

        processGame(players, deck);

        if (dealer.hit(deck)) {
            outputView.printDealerHitSuccess();
        }

        outputView.printCardResult(dealer, players);
        showWinLoseResult(players, dealer);
    }

    private List<Player> setPlayers() {
        List<String> names = inputView.readNames();
        return names.stream()
                .map(Player::new)
                .toList();
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

    private void showWinLoseResult(List<Player> players, Dealer dealer) {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        Map<Player, Result> playerResult = new HashMap<>();

        for (Player player : players) {
            Result result = Result.judge(dealer.getDeck(), player.getDeck());
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, Result.judge(player.getDeck(), dealer.getDeck()));
        }
        outputView.printWinLoseResult(dealerResult, playerResult);
    }

}
