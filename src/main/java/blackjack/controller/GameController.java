package blackjack.controller;

import blackjack.domain.GameMachine;
import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.Map;

public class GameController {

    public void run() {
        GameMachine gameMachine = new GameMachine(CardDeckGenerator.createCardDeck());

        Dealer dealer = gameMachine.createDealer();
        Users users = gameMachine.createUsers(InputView.inputUsers());
        inputBettingMoney(gameMachine, users);
        ResultView.printPlayersCards(dealer, users);

        turnOfUsers(gameMachine, users);
        turnOfDealer(gameMachine, dealer);
        ResultView.printTotalCardResult(dealer, users);

        GameResult gameResult = GameResult.createPlayerGameResult(dealer, users);
        ResultView.printGameResult(gameResult);

        processForFinalRevenue(gameMachine, gameResult);
    }

    private void inputBettingMoney(GameMachine gameMachine, Users users) {
        for (User user : users.getUsers()) {
            gameMachine.putBettingMoney(user, InputView.inputBettingMoney(user));
        }
    }

    private void turnOfUsers(GameMachine gameMachine, Users users) {
        for (User user : users.getUsers()) {
            processForUser(gameMachine, user);
        }
    }

    private void processForUser(final GameMachine gameMachine, final User user) {
        while (user.canDrawCard() && InputView.inputDrawCardAnswer(user)) {
            gameMachine.drawCardToPlayer(user);
            ResultView.printPlayerCards(user);
        }
    }

    private void turnOfDealer(GameMachine gameMachine, Dealer dealer) {
        if (gameMachine.checkPlayerReceiveCard(dealer)) {
            ResultView.printDealerReceiveCard();
        }
    }

    private void processForFinalRevenue(GameMachine gameMachine, GameResult gameResult) {
        Map<String, Integer> userRevenue = gameResult.getUserRevenue(gameMachine.getUserBettingMoney());
        ResultView.printDealerRevenue(gameResult.getDealerRevenue(userRevenue));
        ResultView.printFinalRevenue(userRevenue);
    }
}
