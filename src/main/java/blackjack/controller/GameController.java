package blackjack.controller;

import blackjack.domain.CardDeckGenerator;
import blackjack.domain.GameMachine;
import blackjack.domain.GameResult;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class GameController {

    public void run() {
        GameMachine gameMachine = new GameMachine(CardDeckGenerator.createCardDeckByCardNumber());

        Player dealer = gameMachine.createDealer();
        List<Player> users = gameMachine.createUsers(InputView.inputUsers());
        ResultView.printDealerAndUserCards(users, dealer);

        checkPlayerHaveBlackJack(gameMachine, dealer, users);
        ResultView.printTotalCardResult(dealer, users);
        
        GameResult gameResult = GameResult.createPlayerGameResult(dealer, users);
        ResultView.printGameResult(gameResult);
    }

    private void checkPlayerHaveBlackJack(GameMachine gameMachine, Player dealer, List<Player> users) {
        if (!gameMachine.haveBlackJack(users, dealer)) {
            askReceiveCardToUsers(gameMachine, users);
            receivePlayerCard(gameMachine, dealer);
        }
    }


    private void askReceiveCardToUsers(GameMachine gameMachine, List<Player> players) {
        for (Player user : players) {
            askReceiveCard(gameMachine, user);
        }
    }

    private void askReceiveCard(GameMachine gameMachine, Player user) {
        if (user.isPossibleToPickCard()) {
            Boolean willDrawCard = InputView.inputDrawCardAnswer(user);
            drawCard(gameMachine, user, willDrawCard);
        }
    }

    private void drawCard(GameMachine gameMachine, Player user, Boolean drawCard) {
        if (drawCard) {
            gameMachine.drawCardToPlayer(user);
            ResultView.printUserCards(user);
            askReceiveCard(gameMachine, user);
            return ;
        }
        ResultView.printUserCards(user);
    }

    private void receivePlayerCard(GameMachine gameMachine, Player dealer) {
        if (gameMachine.checkPlayerReceiveCard(dealer)) {
            ResultView.printDealerReceiveCard();
        }
    }
}
