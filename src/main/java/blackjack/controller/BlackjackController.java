package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run() {
        Dealer dealer = new Dealer();
        OutputView.printInputNames();
        List<String> names = InputView.inputNames();
        Users users = new Users(names);

        dealer.distribute(Deck.popTwo());
        users.distributeToEachUser();

        OutputView.printDistribute(users);
        OutputView.printDealerCard(dealer.show());
        OutputView.printUsersCards(users);

        for (User user : users.users()) {
            askForDraw(user);
        }
    }

    private void askForDraw(User user) {
        while (isDrawable(user) && DrawAnswer.isYes(DrawAnswer.of(InputView.inputDrawAnswer()))) {
            user.draw();
            OutputView.printUserCards(user);
        }
    }

    private boolean isDrawable(User user) {
        if (user.isDrawable()) {
            OutputView.printMoreDraw(user);
            return true;
        }
        return false;
    }
}
