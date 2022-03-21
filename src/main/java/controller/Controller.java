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

    public void run() {
        Deck deck = new Deck(Card.getShuffledCards());
        Dealer dealer = new Dealer(new InitCards(deck).getInitCards());
        Players players = createPlayers(deck);
        BettingReceipts bettingReceipts = createBettingReceipt(players);

        printInit(dealer, players);
        draw(deck, dealer, players);
        printResultAndProfit(dealer, players, Results.generateResults(dealer, players), bettingReceipts);
    }

    private Players createPlayers(Deck deck) {
        List<Player> players = new ArrayList<>();
        for (Name name : InputView.inputNames()) {
            players.add(new Player(name, new InitCards(deck).getInitCards()));
        }
        return new Players(players);
    }

    private BettingReceipts createBettingReceipt(Players players) {
        Map<Name, BettingMoney> maps = new LinkedHashMap<>();
        players.forEach(player -> maps.put(player.getName(), new BettingMoney(InputView.inputMoney(player.getName()))));
        return new BettingReceipts(maps);
    }

    private void printInit(Dealer dealer, Players players) {
        OutputView.printInitHands(dealer, players);
        OutputView.printDealerIsBlackJackMessage(dealer);
        OutputView.printPlayerIsBlackJackMessage(players);
    }

    private void draw(Deck deck, Dealer dealer, Players players) {
        drawForPlayers(deck, dealer, players);
        drawForDealer(deck, dealer, players);
    }

    private void drawForPlayers(Deck deck, Dealer dealer, Players players) {
        if (dealer.isBlackJack()) {
            return;
        }
        players.forEach(player -> {
            while (player.isNeedToDraw() && InputView.inputAskDraw(player.getName().getValue())) {
                player.addCard(deck.draw());
                OutputView.printPlayerHand(player.getName().getValue(), player.showHand());
                OutputView.printIfMaxScoreOrBust(player);
            }
        });
    }

    private void drawForDealer(Deck deck, Dealer dealer, Players players) {
        while (players.isNotAllBust() && dealer.isNeedToDraw()) {
            OutputView.printDealerDrawMessage();
            dealer.addCard(deck.draw());
        }
    }

    private void printResultAndProfit(
            Dealer dealer,
            Players players,
            Results results,
            BettingReceipts bettingReceipts
    ) {
        OutputView.printStatuses(dealer, players);
        OutputView.printResult(players.getNames(), results);
        OutputView.printProfit(players.getNames(), Profits.generateProfits(results, bettingReceipts, players));
    }
}
