package controller;

import domain.BlackjackManager;
import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Players;
import domain.player.User;
import domain.player.Users;
import domain.profit.NormalProfitStrategy;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        BlackjackManager blackjackManager = createBlackjackManager();

        distributeInitialCards(blackjackManager);
        hitUntilAllStay(blackjackManager);
        printCardsAndSum(blackjackManager);
        printProfit(blackjackManager);
    }

    private void distributeInitialCards(BlackjackManager blackjackManager) {
        blackjackManager.distributeInitialCards();
        blackjackManager.openInitialCards();
        OutputView.printInitialCards(
                blackjackManager.getDealer(),
                blackjackManager.getUsers()
        );
    }

    private void hitUntilAllStay(BlackjackManager blackjackManager) {
        blackjackManager.allUsersHitUntilStay(InputView::inputWantHit, OutputView::printPlayerCards);
        blackjackManager.dealerHitUntilStay(OutputView::printDealerHitMessage);
    }

    private void printCardsAndSum(BlackjackManager blackjackManager) {
        OutputView.printCardsAndSum(blackjackManager.getDealer(),
                blackjackManager.getUsers(),
                blackjackManager.computePlayerSum());
    }

    private void printProfit(BlackjackManager blackjackManager) {
        OutputView.printProfit(
                blackjackManager.computeDealerProfit(),
                blackjackManager.computeUsersProfit(NormalProfitStrategy.getInstance())
        );
    }

    private BlackjackManager createBlackjackManager() {
        Users users = createUsers(InputView.inputUserNames());
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(dealer, users);
        return new BlackjackManager(players, deck);
    }

    private Users createUsers(List<String> names) {
        List<User> users = new ArrayList<>();
        // 팩터리 패턴 사용할 때 Map<name, bet> 사용
        for (String name : names) {
            int bet = InputView.inputBet(name);
            users.add(new User(name, bet));
        }
        return new Users(users);
    }

    private Players createPlayers(Dealer dealer, Users users) {
        return new Players(dealer, users);
    }
}
