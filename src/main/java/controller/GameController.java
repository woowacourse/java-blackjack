package controller;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;


    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String rawPlayerNames = inputView.readPlayerName();
        List<String> playerNames = Arrays.stream(rawPlayerNames.split(",")).toList();

        Deck deck = new Deck();

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {

            Player player = new Player(playerName, deck.handOutCards());
            players.add(player);
        }

        Dealer dealer = new Dealer(deck.handOutCards());
        printGameStart(playerNames, dealer, players);

        for (Player player : players) {
            processRound(player, deck);
        }


        while (dealer.isReceiveCard()) {
            dealer.addCard(deck.peekCard());
            outputView.printDealerReceiveCard();
        }
    }

    private void printGameStart(List<String> playerNames, Dealer dealer, List<Player> players) {
        outputView.printStartCardMessage(playerNames);
        outputView.printDealerStartCard(dealer.getHoldCards().getFirst());
        outputView.printStartCard(players);
    }

    private void processRound(Player player, Deck deck) {
        String hitOption = inputView.readHitOption(player.getName());
        if (hitOption.equals("y")) {
            player.addCard(deck.peekCard());
        }
        outputView.printCurrentHoldCard(player);

        while (hitOption.equals("y") && !player.isBust()) {
            hitOption = inputView.readHitOption(player.getName());
            if (hitOption.equals("n")) {
                break;
            }
            player.addCard(deck.peekCard());
            outputView.printCurrentHoldCard(player);
        }
    }
}
