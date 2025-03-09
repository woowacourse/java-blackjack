package controller;

import domain.BlackjackMatchResult;
import domain.Cards;
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
    private final Cards deck;

    public BlackJackController(InputView inputView, OutputView outputView, Cards deck) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck = deck;
    }

    public void run() {
        List<Player> players = setPlayers();
        Dealer dealer = new Dealer();

        prepareGame(dealer, players);

        outputView.printInitialCards(dealer, players);

        processPlayersTurn(players);

        processDealerTurn(dealer);

        outputView.printCardResult(dealer, players);
        showWinLoseResult(players, dealer);
    }

    private List<Player> setPlayers() {
        List<String> names = inputView.readNames();
        return names.stream()
                .map(Player::new)
                .toList();
    }

    private void prepareGame(Dealer dealer, List<Player> players) {
        dealer.prepareGame(deck);
        players.forEach(player -> player.prepareGame(deck));
    }

    private void processPlayersTurn(List<Player> players) {
        for (Player player : players) {
            selectChoice(player);
        }
    }

    private void selectChoice(Player player) {
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

    private void processDealerTurn(Dealer dealer) {
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
            BlackjackMatchResult result = BlackjackMatchResult.judge(dealer.getCards(), player.getCards());
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, BlackjackMatchResult.judge(player.getCards(), dealer.getCards()));
        }
        outputView.printWinLoseResult(dealerResult, playerResult);
    }

}
