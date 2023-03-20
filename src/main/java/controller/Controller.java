package controller;

import domain.Betting;
import domain.Bettings;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.PlayerName;
import domain.PlayerNames;
import domain.Players;
import domain.Result;
import game.Blackjack;
import view.InputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static view.InputView.printErrorMessage;
import static view.InputView.readBetting;
import static view.InputView.readIsHit;
import static view.InputView.readPlayerNames;
import static view.OutputView.printBenefits;
import static view.OutputView.printDealerHitMessage;
import static view.OutputView.printGamblersCards;
import static view.OutputView.printInitialPickGuideMessage;
import static view.OutputView.printResult;
import static view.OutputView.printScores;
import static view.OutputView.printSingleGambler;


public class Controller {

    public void playBlackjack() {
        PlayerNames playerNames = getPlayerNames();
        Bettings bettings = getBettings(playerNames);

        Blackjack blackjack = new Blackjack(createPlayers(playerNames));
        Players players = blackjack.getPlayers();
        Dealer dealer = blackjack.getDealer();
        printInitialGuideMessages(players, dealer);
        Result result = playBlackjack(players, dealer, blackjack, bettings);
        printAllOutputs(players, dealer, result);
    }

    private void printInitialGuideMessages(Players players, Dealer dealer) {
        printInitialPickGuideMessage(players);
        printGamblersCards(players, dealer);
    }

    private PlayerNames getPlayerNames() {
        try {
            List<String> playerNamesInput = readPlayerNames();
            return new PlayerNames(playerNamesInput);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getPlayerNames();
        }
    }

    private Bettings getBettings(PlayerNames playerNames) {
        List<Betting> bettings = playerNames.getStringPlayerNames()
                .stream()
                .map(this::getBetting)
                .collect(Collectors.toList());

        return new Bettings(bettings);
    }

    private Betting getBetting(String playerName) {
        try {
            String readBetting = readBetting(playerName);
            return new Betting(readBetting);
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getBetting(playerName);
        }
    }

    private List<Player> createPlayers(PlayerNames playerNames) {
        return playerNames.getPlayerNames()
                .stream()
                .map(playerName -> new Player(playerName, new Cards(new ArrayList<>())))
                .collect(Collectors.toList());
    }

    private Result playBlackjack(Players players, Dealer dealer, Blackjack blackjack, Bettings bettings) {
        hitOrStandByPlayers(players, blackjack);
        hitOrStandByDealer(dealer, blackjack);

        return blackjack.createResult(players, dealer, bettings);
    }

    private void hitOrStandByPlayers(Players players, Blackjack blackjack) {
        for (Player player : players.getPlayers()) {
            boolean isHit;
            do {
                isHit = getIsHit(player);
                blackjack.hitOrStandByPlayer(player, isHit);
                printSingleGambler(player);
            } while (isHit && isPickAble(player));
        }
    }

    public boolean getIsHit(Player player) {
        try {
            return readIsHit(player.getName());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getIsHit(player);
        }
    }

    private boolean isPickAble(Player player) {
        return !player.isBustedGambler();
    }

    public void hitOrStandByDealer(Dealer dealer, Blackjack blackjack) {
        while (dealer.isDealerHit()) {
            blackjack.hitOrStandByDealer(dealer);
            printDealerHitMessage();
        }
    }

    private void printAllOutputs(Players players, Dealer dealer, Result result) {
        printScores(players, dealer);
        printResult(result);
        printBenefits(players.getPlayers(), dealer, result.getBenefits());
    }
}
