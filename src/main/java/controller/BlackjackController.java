package controller;

import java.util.List;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.GameStatus;
import model.Player;
import model.PlayerResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final Dealer dealer;
    private CardDispenser cardDispenser;

    public BlackjackController() {
        this.dealer = new Dealer();
    }

    public void run() {
        List<String> names = InputView.readPlayerNames();
        List<Player> players = createPlayers(names);
        createCardDispenser();
        distributeCard(players);

        hitOrStandByPlayers(players);
        hitUntilStandByDealer();

        printFinalCards(players);
        printGameResult(players);
    }

    private List<Player> createPlayers(List<String> names) {
        return names.stream()
                .map(Player::new)
                .toList();
    }

    private void createCardDispenser() {
        Cards cards = Cards.createShuffledDeck();
        this.cardDispenser = new CardDispenser(cards);
    }

    private void distributeCard(List<Player> players) {
        OutputView.printCardOpen(players);

        cardDispenser.dispenseStartingCards(dealer);
        OutputView.printCardByDealer(dealer);

        for (Player player : players) {
            cardDispenser.dispenseStartingCards(player);
        }
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
        cardDispenser.dispenseOneCard(player);
        OutputView.printCardByPlayer(player);
    }

    private void hitUntilStandByDealer() {
        while (dealer.canHit()) {
            OutputView.printToOpenDealerNewCard(dealer.getName());
            cardDispenser.dispenseOneCard(dealer);
        }
    }

    private void printFinalCards(List<Player> players) {
        OutputView.printBlank();
        OutputView.printCardByPlayerWithScore(dealer);
        players.forEach(OutputView::printCardByPlayerWithScore);
    }

    private void printGameResult(List<Player> players) {
        PlayerResult playerResult = PlayerResult.judgeByPlayer(dealer, players);
        int winCount = playerResult.countByStatus(GameStatus.LOSE);
        int loseCount = playerResult.countByStatus(GameStatus.WIN);
        int drawCount = playerResult.countByStatus(GameStatus.DRAW);

        OutputView.printFinalResultHeader();
        OutputView.printDealerResult(winCount, loseCount, drawCount);
        OutputView.printResultByPlayers(playerResult.getResult());
    }
}
