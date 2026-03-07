package controller;

import java.util.List;
import model.Cards;
import model.Dealer;
import model.GameStatus;
import model.Player;
import model.PlayerResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final Dealer dealer;

    public BlackjackController() {
        this.dealer = new Dealer(Cards.createDeck());
    }

    public void run() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = createPlayers(names);
        distributeCard(players);

        hitOrStandByPlayers(players);
        hitUntilStandByDealer(dealer);

        printFinalCards(dealer, players);
        printGameResult(dealer, players);
    }

    private List<Player> createPlayers(List<String> names) {
        return names.stream()
                .map(Player::new)
                .toList();
    }

    private void distributeCard(List<Player> players) {
        dealer.distributeInitialCards(players);

        OutputView.printCardOpen(players);
        OutputView.printCardByDealer(dealer);
        OutputView.printCardByPlayers(players);
    }

    private void hitOrStandByPlayers(List<Player> players) {
        for (Player player : players) {
            chooseHitOrStand(player);
        }
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
            distributeMoreOneCard(player);
            drawCard = true;
        }

        if (!drawCard) {
            OutputView.printCardByPlayer(player);
        }
    }

    private void distributeMoreOneCard(Player player) {
        dealer.distributeCard(player);
        OutputView.printCardByPlayer(player);
    }

    private void hitUntilStandByDealer(Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            dealer.distributeCard();
        }
    }

    private void printFinalCards(Dealer dealer, List<Player> players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }

    private void printGameResult(Dealer dealer, List<Player> players) {
        PlayerResult playerResult = PlayerResult.judgeByPlayer(dealer, players);
        int winCount = playerResult.countByStatus(GameStatus.LOSE);
        int loseCount = playerResult.countByStatus(GameStatus.WIN);
        int drawCount = playerResult.countByStatus(GameStatus.DRAW);

        OutputView.printFinalResultHeader();
        OutputView.printDealerResult(winCount, loseCount, drawCount);
        OutputView.printResultByPlayers(playerResult.getResult());
    }
}
