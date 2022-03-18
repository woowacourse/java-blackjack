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

        playTurn(gameMachine, dealer, users);
        ResultView.printTotalCardResult(dealer, users);

        GameResult gameResult = createGameResult(dealer, users);
        processForFinalRevenue(gameMachine, gameResult);
    }

    private void inputBettingMoney(GameMachine gameMachine, Users users) {
        for (User user : users.getUsers()) {
            gameMachine.putBettingMoney(user, InputView.inputBettingMoney(user));
        }
    }

    private void playTurn(GameMachine gameMachine, Dealer dealer, Users users) {
        turnOfUsers(gameMachine, users);
        turnOfDealer(gameMachine, dealer);
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
        if (dealer.canDrawCard()) {
            gameMachine.drawCardToPlayer(dealer);
            ResultView.printDealerReceiveCard();
        }
    }

    private GameResult createGameResult(Dealer dealer, Users users) {
        GameResult gameResult = GameResult.createPlayerGameResult(dealer, users);
        ResultView.printGameResult(gameResult);
        return gameResult;
    }

    private void processForFinalRevenue(GameMachine gameMachine, GameResult gameResult) {
        Map<User, Integer> userRevenue = gameMachine.getUserRevenue(gameResult.getUserResult());
        ResultView.printDealerRevenue(gameMachine.getDealerRevenue());
        ResultView.printFinalRevenue(userRevenue);
    }
}
