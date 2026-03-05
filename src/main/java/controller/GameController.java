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

        List<Card> cards = deck.handOutCards();
        System.out.println(cards);
        Dealer dealer = new Dealer(cards);

        System.out.println(dealer);
        outputView.printStartCardMessage(playerNames);
        System.out.println(dealer.getHoldCards().getFirst());
        outputView.printDealerStartCard(dealer.getHoldCards().getFirst());
        outputView.printStartCard(players);

        for (Player player : players) {
            String hitOption = inputView.readHitOption(player.getName());
            if(hitOption.equals("y")) {
                player.addCard(deck.peekCard());
            }
            outputView.printCurrentHoldCard(player);

            while (hitOption.equals("y")) {
                hitOption = inputView.readHitOption(player.getName());
                if (hitOption.equals("n")) {
                    break;
                }
                player.addCard(deck.peekCard());
                outputView.printCurrentHoldCard(player);
            }
        }
    }
}
