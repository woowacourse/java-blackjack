package controller;

import controller.result.ResultReporter;
import java.util.ArrayList;
import java.util.List;
import model.BlackjackService;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final BlackjackService blackjackService;
    private final ResultReporter resultReporter;

    public BlackjackController(BlackjackService blackjackService, ResultReporter resultReporter) {
        this.blackjackService = blackjackService;
        this.resultReporter = resultReporter;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Players players = createPlayers();

        drawInitCards(dealer, players);
        drawMoreCardByPlayer(dealer, players);

        printFinalCards(dealer, players);
        reportResult(dealer, players);
    }

    private Players createPlayers() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            int betAmount = InputView.readBetAmount(name);
            players.add(new Player(name, betAmount));
        }
        return new Players(players);
    }

    private void drawMoreCardByPlayer(Dealer dealer, Players players) {
        players.forEach(this::chooseHitOrStand);
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer);
            blackjackService.drawOneCard(dealer);
        }
    }

    private void drawInitCards(Dealer dealer, Players players) {
        blackjackService.drawTwoCards(dealer, players);
        OutputView.printCardOpen(players);
        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayers(players);
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player);
        return Continuation.from(inputCommand);
    }

    private void chooseHitOrStand(Player player) {
        boolean printed = false;
        while (canHitMore(player)) {
            blackjackService.drawOneCard(player);
            OutputView.printCardByPlayer(player);
            printed = true;
        }

        if (!printed) {
            OutputView.printCardByPlayer(player);
        }
    }

    private void printFinalCards(Dealer dealer, Players players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }

    private void reportResult(Dealer dealer, Players players) {
        PlayerResult playerResult = Judgement.judgeByPlayer(dealer, players);
        resultReporter.report(playerResult);
    }
}
