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
        this.players = createPlayers(deck);
        this.bettingReceipts = createBettingReceipt(players);
    }

    public void run() {
        printInit();
        draw();
        printResultAndProfit(Results.generateResults(dealer, players), bettingReceipts, players);
    }

    private BettingReceipts createBettingReceipt(Players players) {
        Map<Name, BettingMoney> maps = new LinkedHashMap<>();
        for (Name name : players.getNames()) {
            maps.put(name, new BettingMoney(InputView.inputMoney(name)));
        }
        return new BettingReceipts(maps);
    }

    private Players createPlayers(Deck deck) {
        List<Player> players = new ArrayList<>();
        for (Name name : InputView.inputNames()) {
            players.add(new Player(name, new InitCards(deck).getInitCards()));
        }
        return new Players(players);
    }

    private void printInit() {
        OutputView.printInitHands(dealer, players);
        OutputView.printDealerIsBlackJackMessage(dealer);
        OutputView.printPlayerIsBlackJackMessage(players);
    }

    private void draw() {
        drawForPlayers(deck, dealer, players);
        drawForDealer(deck, dealer, players);
    }

    private void drawForPlayers(Deck deck, Dealer dealer, Players players) {
        if (dealer.isBlackJack()) {
            return;
        }
        for (Name name : players.getNames()) {
            drawForPlayer(name, deck, players);
        }
    }

    private void drawForPlayer(Name name, Deck deck, Players players) {
        while (players.isNeedToDrawByName(name) && InputView.inputAskDraw(name.getName())) {
            players.addCardByName(name, deck.draw());
            OutputView.printPlayerHand(name, players);
            OutputView.printIfMaxScoreOrBust(name, players);
        }
    }

    private void drawForDealer(Deck deck, Dealer dealer, Players players) {
        while (players.isNotAllBust() && dealer.isNeedToDraw()) {
            OutputView.printDealerDrawMessage();
            dealer.addCard(deck.draw());
        }
    }

    private void printResultAndProfit(Results results, BettingReceipts bettingReceipts, Players players) {
        OutputView.printStatuses(dealer, players);
        OutputView.printResult(players.getNames(), results);
        OutputView.printProfit(players.getNames(), Profits.generateProfits(results, bettingReceipts, players));
    }
}
