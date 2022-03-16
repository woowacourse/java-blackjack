package controller;

import domain.betting.BettingReceipt;
import domain.betting.BettingMoney;
import domain.card.Cards;
import domain.card.Deck;
import domain.card.InitCards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Result;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class Controller {

    public void run() {
        Deck deck = new Deck(Cards.getInstance().getCards());
        Dealer dealer = new Dealer(new InitCards(deck).getInitCards());
        Players players = new Players(createPlayers(deck));
        BettingReceipt bettingReceipt = new BettingReceipt(createBettingReceipt(players));

        OutputView.printInitHands(dealer, players);

        if (dealer.isBlackJack()) {
            OutputView.printDealerIsBlackJackMessage();
            printResultAndProfit(players.generateResultAtDealerBlackJack(dealer), bettingReceipt, players);
            return;
        }
        OutputView.printPlayerIsBlackJackMessage(players);

        drawForPlayers(deck, players);
        drawForDealer(deck, dealer, players);
        OutputView.printStatuses(dealer, players);
        printResultAndProfit(players.generateResultAtFinal(dealer), bettingReceipt, players);
    }

    private List<Player> createPlayers(Deck deck) {
        List<Player> players = new ArrayList<>();
        for (Name name : InputView.inputNames()) {
            players.add(new Player(name, new InitCards(deck).getInitCards()));
        }
        return players;
    }

    private Map<Name, BettingMoney> createBettingReceipt(Players players) {
        Map<Name, BettingMoney> bettingReceipt = new LinkedHashMap<>();
        for (Name name : players.getNames()) {
            bettingReceipt.put(name, new BettingMoney(InputView.inputMoney(name)));
        }
        return bettingReceipt;
    }

    private void drawForPlayers(Deck deck, Players players) {
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

    private void printResultAndProfit(Result result, BettingReceipt bettingReceipt, Players players) {
        OutputView.printResult(players.getNames(), result);
        OutputView.printProfit(players.getNames(), bettingReceipt.generateProfits(result, players));
    }
}
