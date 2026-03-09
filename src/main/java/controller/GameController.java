package controller;

import domain.Dealer;
import domain.Deck;
import domain.GameResult;
import domain.Judgement;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputParser;
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
        List<String> playerNames = getPlayerNames();

        Deck deck = new Deck();
        Dealer dealer = new Dealer(deck.handOutCards());

        Players players = getPlayers(playerNames, deck);
        printGameStart(playerNames, dealer, players);
        receiveMoreCard(players, deck, dealer);

        outputView.printFinalScore(dealer, players);

        Judgement judgement = new Judgement();

        Map<String, GameResult> playerResults = judgement.calculatePlayerResults(players, dealer);
        Map<GameResult, Integer> dealerResults = judgement.calculateDealerResults(playerResults);
        outputView.printDealerFinalCount(dealerResults);
        outputView.printPlayerFinalResults(playerResults);
    }

    private List<String> getPlayerNames() {
        String rawPlayerNames = inputView.readPlayerName();
        return InputParser.parsePlayerNames(rawPlayerNames);
    }

    private Players getPlayers(List<String> playerNames, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = new Player(playerName, deck.handOutCards());
            players.add(player);
        }
        return new Players(players);
    }

    private void printGameStart(List<String> playerNames, Dealer dealer, Players players) {
        outputView.printStartCardMessage(playerNames);
        outputView.printDealerStartCard(dealer.getHand().getFirst());
        outputView.printStartCard(players);
    }

    private void receiveMoreCard(Players players, Deck deck, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            processRound(player, deck);
        }

        while (dealer.isReceiveCard()) {
            dealer.addCard(deck.peekCard());
            outputView.printDealerReceiveCard();
        }
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
