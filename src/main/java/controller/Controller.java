package controller;

import domain.betting.BettingMoney;
import domain.betting.BettingReceipts;
import domain.betting.Profits;
import domain.card.Card;
import domain.card.Deck;
import domain.card.InitCards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Results;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class Controller {

    Deck deck;
    Dealer dealer;
    Players players;
    BettingReceipts bettingReceipts;

    public Controller() {
        this.deck = new Deck(Card.getShuffledCards());
        this.dealer = new Dealer(new InitCards(deck).getInitCards());
        this.players = createPlayers();
        this.bettingReceipts = createBettingReceipt();
    }

    public void run() {
        printInit();
        draw();
        printResultAndProfit(Results.generateResults(dealer, players));
    }

    private Players createPlayers() {
        List<Player> players = new ArrayList<>();
        for (Name name : InputView.inputNames()) {
            players.add(new Player(name, new InitCards(deck).getInitCards()));
        }
        return new Players(players);
    }

    private BettingReceipts createBettingReceipt() {
        Map<Name, BettingMoney> maps = new LinkedHashMap<>();
        players.forEach(player -> maps.put(player.getName(), new BettingMoney(InputView.inputMoney(player.getName()))));
        return new BettingReceipts(maps);
    }

    private void printInit() {
        OutputView.printInitHands(dealer, players);
        OutputView.printDealerIsBlackJackMessage(dealer);
        OutputView.printPlayerIsBlackJackMessage(players);
    }

    private void draw() {
        drawForPlayers();
        drawForDealer();
    }

    private void drawForPlayers() {
        if (dealer.isBlackJack()) {
            return;
        }
        players.forEach(player -> {
            while (player.isNeedToDraw() && InputView.inputAskDraw(player.getName().getValue())) {
                player.addCard(deck.draw());
                OutputView.printPlayerHand(player.getName().getValue(), player.showHand());
                OutputView.printIfMaxScoreOrBust(players);
            }
        });
    }

    private void drawForDealer() {
        while (players.isNotAllBust() && dealer.isNeedToDraw()) {
            OutputView.printDealerDrawMessage();
            dealer.addCard(deck.draw());
        }
    }

    private void printResultAndProfit(Results results) {
        OutputView.printStatuses(dealer, players);
        OutputView.printResult(players.getNames(), results);
        OutputView.printProfit(players.getNames(), Profits.generateProfits(results, bettingReceipts, players));
    }
}
