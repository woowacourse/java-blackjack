package controller;

import domain.card.CardDeck;
import domain.card.StandardCardsInitializer;
import domain.gamer.Dealer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import domain.rule.BlackjackMatchResult;
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
        CardDeck deck = new CardDeck(new StandardCardsInitializer());
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
                .map(Nickname::new)
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
        return player.canHit() && YES_SIGN.equals(getYesOrNo(player));
    }

    private String getYesOrNo(Player player) {
        return inputView.readYesOrNo(player);
    }

    private void processDealerTurn(Dealer dealer, CardDeck deck) {
        if (dealer.canHit()) {
            dealer.hit(deck);
            outputView.printDealerHitSuccess();
        }
    }

    private void showWinLoseResult(List<Player> players, Dealer dealer) {
        Map<BlackjackMatchResult, Integer> dealerResult = new EnumMap<>(BlackjackMatchResult.class);
        Map<Player, BlackjackMatchResult> playerResult = new HashMap<>();

        for (Player player : players) {
            BlackjackMatchResult result = dealer.determineMatchResultAgainst(player);
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
            playerResult.put(player, result.reverse());
        }
        outputView.printWinLoseResult(dealerResult, playerResult);
    }

}
