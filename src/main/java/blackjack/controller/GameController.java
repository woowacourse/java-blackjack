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

        if (!gameMachine.haveBlackJack(users, dealer)) {
            askReceiveCardToUsers(gameMachine, users);
            receiveDealerCard(gameMachine, dealer);
        }
        ResultView.printTotalCardResult(dealer, users);
        
        GameResult gameResult = GameResult.createPlayerGameResult(dealer, users);
        ResultView.printGameResult(gameResult);
    }

    private void askReceiveCardToUsers(GameMachine gameMachine, List<Player> players) {
        for (Player user : players) {
            askReceiveCard(gameMachine, user);
        }
    }

    private void askReceiveCard(GameMachine gameMachine, Player user) {
        if (user.isPossibleToPickCard()) {
            Boolean getCard = InputView.inputDrawCardAnswer(user);
            drawCard(gameMachine, user, getCard);
        }
    }

    private void drawCard(GameMachine gameMachine, Player user, Boolean getCard) {
        if (getCard) {
            gameMachine.drawCardToUser(user);
            ResultView.printUserCards(user);
            askReceiveCard(gameMachine, user);
        } else if (!getCard) {
            ResultView.printUserCards(user);
        }
    }

    private void receiveDealerCard(GameMachine gameMachine, Player dealer) {
        if (gameMachine.checkDealerReceiveCard(dealer)) {
            ResultView.printDealerReceiveCard();
        }
    }
}
