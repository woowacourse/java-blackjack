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
import model.service.GameManager;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final GameManager gameManager;

    public BlackjackController() {
        CardFactory cardFactory = new CardFactory();
        CardShuffler cardShuffler = new SimpleCardShuffler();

        List<Card> fullCards = cardFactory.createFullCards();
        List<Card> shuffledCard = cardShuffler.shuffle(fullCards);
        Deck deck = new Deck(shuffledCard);
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
        hitUntilStandByDealer(dealer);

        printFinalCards(dealer, players);
        printGameResult(dealer, players);
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player.getName());
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

    private void hitUntilStandByDealer(Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            gameManager.distributeCard(dealer);
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
