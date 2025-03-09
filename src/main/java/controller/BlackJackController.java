package controller;

import domain.BlackjackMatchResult;
import domain.CardDeck;
import domain.CardsInitializer;
import domain.Dealer;
import domain.Player;
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
        CardDeck deck = new CardDeck(cardsInitializer.initialize());
        List<Player> players = setPlayers();
        Dealer dealer = new Dealer();

        prepareGame(dealer, players, deck);

        outputView.printInitialCards(dealer, players);

        processPlayersTurn(players, deck);

        processDealerTurn(dealer, deck);

        outputView.printCardResult(dealer, players);
        showWinLoseResult(players, dealer);
    }

    private List<Player> setPlayers() {
        List<String> names = inputView.readNames();
        return names.stream()
                .map(Player::new)
                .toList();
    }

    private void prepareGame(Dealer dealer, List<Player> players, CardDeck deck) {
        dealer.prepareGame(deck);
        players.forEach(player -> player.prepareGame(deck));
    }

    private void processPlayersTurn(List<Player> players, CardDeck deck) {
        for (Player player : players) {
            selectChoice(player, deck);
        }
    }

    private void selectChoice(Player player, CardDeck deck) {
        while (canHit(player)) {
            player.hit(deck);
            outputView.printCards(player);
        }
        if (!player.isBurst()) {
            outputView.printCards(player);
        }
    }

    private boolean canHit(Player player) {
        return !player.isBurst() && YES_SIGN.equals(getYesOrNo(player));
    }

    private String getYesOrNo(Player player) {
        return inputView.readYesOrNo(player);
    }

    private void processDealerTurn(Dealer dealer, CardDeck deck) {
        try {
            dealer.hit(deck);
            outputView.printDealerHitSuccess();
        } catch (IllegalStateException e) {
            outputView.printDealerHitFail();
        }
    }

    private void showWinLoseResult(List<Player> players, Dealer dealer) {
        Map<BlackjackMatchResult, Integer> dealerResult = new EnumMap<>(BlackjackMatchResult.class);
        Map<Player, BlackjackMatchResult> playerResult = new HashMap<>();

        for (Player player : players) {
            BlackjackMatchResult result = BlackjackMatchResult.judge(dealer.getHand(), player.getHand());
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, BlackjackMatchResult.judge(player.getHand(), dealer.getHand()));
        }
        outputView.printWinLoseResult(dealerResult, playerResult);
    }

}
