package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run(){
        Dealer dealer = new Dealer();
        OutputView.printInputNames();
        List<String> names = InputView.inputNames();
        Users users = new Users(names);

        dealer.distribute(Deck.popTwo());
        users.distributeToEachUser();

        OutputView.printDistribute(users);
        OutputView.printDealerCard(dealer.show());
        OutputView.printUsersCards(users);
    }
}
