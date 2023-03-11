package controller;

import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.PlayerName;
import domain.Players;
import domain.Result;
import game.Blackjack;

import java.util.ArrayList;
import java.util.List;

import static view.InputView.printErrorMessage;
import static view.InputView.readIsHit;
import static view.InputView.readPlayerNames;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;
import static view.OutputView.printSingleGambler;


public class Controller {

    public static boolean getIsHit(Player player) {
        try {
            return readIsHit(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getIsHit(player);
        }
    }

    public static void printHitOrStandByPlayer(Player player) {
        printSingleGambler(player);
    }

    public static void printDealerHit() {
        printDealerHitMessage();
    }

    public void playBlackjack() {
        Players players = getValidPlayerNames();
        Dealer dealer = new Dealer(new Cards(new ArrayList<>()));

        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);

        Blackjack blackjack = new Blackjack(players, dealer);
        Result result = playBlackjack(players, dealer, blackjack);

        printScores(players, dealer);
        printResult(result);
    }

    private Players getValidPlayerNames() {
        try {
            List<String> playerNames = readPlayerNames();
            List<Player> player = getPlayerList(playerNames);
            return new Players(player);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getValidPlayerNames();
        }
    }

    private List<Player> getPlayerList(List<String> list) {
        List<Player> player = new ArrayList<>();
        for (String playerName : list) {
            player.add(new Player(new PlayerName(playerName), new Cards(new ArrayList<>())));
        }
        return player;
    }

    private Result playBlackjack(Players players, Dealer dealer, Blackjack blackjack) {
        hitOrStandByPlayers(players, blackjack);
        hitOrStandByDealer(dealer, blackjack);
        blackjack.createResult(players, dealer);

        return blackjack.getResult();
    }

    private void hitOrStandByPlayers(Players players, Blackjack blackjack) {
        for (Player player : players.getPlayers()) {
            boolean isHit;
            do {
                isHit = getIsHit(player);
                blackjack.hitOrStandByPlayer(player, isHit);
                printHitOrStandByPlayer(player);
            } while (isHit && isPickAble2(player));
        }
    }

    private boolean isPickAble2(Player player) {
        return !player.isBustedGambler();
    }

    public void hitOrStandByDealer(Dealer dealer, Blackjack blackjack) {
        while (dealer.isDealerHit()) {
            blackjack.hitOrStandByDealer(dealer);
            printDealerHit();
        }
    }
}
