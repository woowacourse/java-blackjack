package blackjack.controller;

import static blackjack.util.ExceptionHandler.retryUntilSuccess;

import blackjack.model.card.HitCommand;
import blackjack.model.card.CardProvider;
import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.gameresult.PlayersGameResult;
import blackjack.model.user.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;


public class BlackjackController {

    private final CardProvider cardProvider;

    public BlackjackController(CardProvider cardProvider) {
        this.cardProvider = cardProvider;
    }

    public void run() {
        Users users = retryUntilSuccess(this::createUsers);

        provideInitCardsAndPrint(users);

        hit(users);

        printHandStatus(users);

        printGameResult(users);

        InputView.closeScanner();
    }

    private Users createUsers() {
        String input = InputView.readPlayerName();
        return new Users(input);
    }

    private void provideInitCardsAndPrint(Users users) {
        cardProvider.provideInitCards(users);
        OutputView.printInitCards(users);
    }

    private void hit(Users users) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();

        for (Player player : players) {
            while (retryUntilSuccess(() -> checkY(player)) && checkAddCard(player)) {
                cardProvider.provideOneCard(player);
                OutputView.printPlayerCards(player);
            }
        }

        while (dealer.isHitAvailable()) {
            cardProvider.provideOneCard(dealer);
            OutputView.printDealerHit();
        }
    }

    private void printHandStatus(Users users) {
        OutputView.printHandStatus(users);
    }

    private void printGameResult(Users users) {
        PlayersGameResult playersGameResult = users.determineWinner();
        OutputView.printGameResult(playersGameResult, users);
    }

    private boolean checkY(Player player) {
        String input = InputView.readCardAdd(player).trim();
        HitCommand hitCommand = new HitCommand(input);
        return hitCommand.isY();
    }

    private boolean checkAddCard(Player player) {
        if (player.isHitAvailable()) {
            return true;
        }
        OutputView.printCantHit();
        return false;
    }
}
