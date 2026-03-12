package controller;

import java.util.List;
import model.Dealer;
import model.DealerResult;
import model.Player;
import model.PlayerResult;
import model.Players;
import model.card.Card;
import model.card.CardShuffler;
import model.card.Deck;
import model.card.SimpleCardShuffler;
import model.service.CardFactory;
import model.service.GameManager;
import model.service.Judgement;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final GameManager gameManager;

    public BlackjackController() {
        CardShuffler cardShuffler = new SimpleCardShuffler();
        List<Card> fullCards = CardFactory.createFullCards();
        List<Card> shuffledCards = cardShuffler.shuffle(fullCards);
        Deck deck = new Deck(shuffledCards);
        this.gameManager = new GameManager(deck);;
    }

    public void run() {
        Dealer dealer = new Dealer();
        List<String> names = InputView.readPlayerNames();
        Players players = Players.from(names);

        gameManager.distributeInitialCards(dealer, players);
        OutputView.printCardOpen(players);
        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayers(players);

        players.forEach(this::chooseHitOrStand);
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer);
            gameManager.distributeCard(dealer);
        }

        printFinalCards(dealer, players);
        printGameResult(dealer, players);
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player);
        return Continuation.from(inputCommand);
    }

    private void chooseHitOrStand(Player player) {
        boolean drawCard = false;
        while (canHitMore(player)) {
            gameManager.distributeCard(player);
            OutputView.printCardByPlayer(player);
            drawCard = true;
        }

        if (!drawCard) {
            OutputView.printCardByPlayer(player);
        }
    }

    private void printFinalCards(Dealer dealer, Players players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }

    private void printGameResult(Dealer dealer, Players players) {
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, players);
        DealerResult dealerResult = Judgement.judgeByDealer(playerResult);

        OutputView.printFinalResultHeader();
        OutputView.printResultByDealer(dealerResult);
        OutputView.printResultByPlayers(playerResult);
    }
}
