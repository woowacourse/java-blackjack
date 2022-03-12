package blackjack.controller;

import blackjack.domain.CardDeckGenerator;
import blackjack.domain.GameMachine;
import blackjack.domain.GameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class GameController {

    public void run() {
        GameMachine gameMachine = new GameMachine(CardDeckGenerator.createCardDeckByCardNumber());

        Dealer dealer = gameMachine.createDealer();
        List<User> users = gameMachine.createUsers(InputView.inputUsers());
        ResultView.printPlayersCards(dealer, users);

        checkPlayerHaveBlackJack(gameMachine, dealer, users);
        ResultView.printTotalCardResult(dealer, users);

        GameResult gameResult = GameResult.createPlayerGameResult(dealer, users);
        ResultView.printGameResult(gameResult);
    }

    private void checkPlayerHaveBlackJack(GameMachine gameMachine, Dealer dealer, List<User> users) {
        if (!gameMachine.haveBlackJack(users, dealer)) {
            askReceiveCardToUsers(gameMachine, users);
            receivePlayerCard(gameMachine, dealer);
        }
    }

    private void askReceiveCardToUsers(GameMachine gameMachine, List<User> users) {
        for (User user : users) {
            askReceiveCard(gameMachine, user);
        }
    }

    private void askReceiveCard(GameMachine gameMachine, User user) {
        if (user.isPossibleToPickCard()) {
            boolean willDrawCard = InputView.inputDrawCardAnswer(user);
            drawCard(gameMachine, user, willDrawCard);
        }
    }

    private void drawCard(GameMachine gameMachine, User user, boolean drawCard) {
        if (drawCard) {
            gameMachine.drawCardToPlayer(user);
            ResultView.printPlayerCards(user);
            askReceiveCard(gameMachine, user);
            return;
        }
        ResultView.printPlayerCards(user);
    }

    private void receivePlayerCard(GameMachine gameMachine, Dealer dealer) {
        if (gameMachine.checkPlayerReceiveCard(dealer)) {
            ResultView.printDealerReceiveCard();
        }
    }
}
