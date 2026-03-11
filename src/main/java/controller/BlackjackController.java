package controller;

import java.util.List;
import model.Dealer;
import model.GameStatus;
import model.Player;
import model.PlayerResult;
import model.Players;
import model.card.Card;
import model.card.CardShuffler;
import model.card.Deck;
import model.card.SimpleCardShuffler;
import model.service.CardFactory;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        CardFactory cardFactory = new CardFactory();
        CardShuffler cardShuffler = new SimpleCardShuffler();

        List<Card> fullCards = cardFactory.createFullCards();
        List<Card> shuffledCard = cardShuffler.shuffle(fullCards);
        Deck deck = new Deck(shuffledCard);
        Dealer dealer = new Dealer(deck);

        List<String> names = InputView.readPlayerNames();
        Players players = Players.from(names);
        distributeCard(dealer, players);

        hitOrStandByPlayers(dealer, players);
        hitUntilStandByDealer(dealer);

        printFinalCards(dealer, players);
        printGameResult(dealer, players);
    }

    private void distributeCard(Dealer dealer, Players players) {
        dealer.distributeInitialCards(players.getPlayers());

        OutputView.printCardOpen(players);
        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayers(players);
    }

    private void hitOrStandByPlayers(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            chooseHitOrStand(dealer, player);
        }
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player.getName());
        return Continuation.from(inputCommand);
    }

    private void chooseHitOrStand(Dealer dealer, Player player) {
        boolean drawCard = false;
        while (canHitMore(player)) {
            distributeMoreOneCard(dealer, player);
            drawCard = true;
        }

        if (!drawCard) {
            OutputView.printCardByPlayer(player);
        }
    }

    private void distributeMoreOneCard(Dealer dealer, Player player) {
        dealer.distributeCard(player);
        OutputView.printCardByPlayer(player);
    }

    private void hitUntilStandByDealer(Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            dealer.distributeCard();
        }
    }

    private void printFinalCards(Dealer dealer, Players players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.getPlayers().forEach(OutputView::printCardByPlayerWithScore);
    }

    private void printGameResult(Dealer dealer, Players players) {
        PlayerResult playerResult = PlayerResult.judgeByPlayer(dealer, players.getPlayers());
        int winCount = playerResult.countByStatus(GameStatus.LOSE);
        int loseCount = playerResult.countByStatus(GameStatus.WIN);
        int drawCount = playerResult.countByStatus(GameStatus.DRAW);

        OutputView.printFinalResultHeader();
        OutputView.printDealerResult(winCount, loseCount, drawCount);
        OutputView.printResultByPlayers(playerResult.getResult());
    }
}
