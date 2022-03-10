package blackjack.controller;

import blackjack.domain.Blackjack;

import static blackjack.view.InputView.inputNames;
import static blackjack.view.InputView.requestHitOrNot;
import static blackjack.view.OutputView.*;

public class BlackjackController {

    private final Blackjack blackjack;

    public BlackjackController() {
        blackjack = new Blackjack(inputNames());
    }

    public void run() {
        init();
        hitOrStayForAllPlayers();
        addCardForDealerIfNeed();
        printTotalScore(blackjack.generateAllResultDTO());
        printTotalResult(blackjack.calculateTotalResult());
    }

    private void init() {
        blackjack.firstDistribute();
        printFirstDistribute(blackjack.generateAllCurrentCardsDTO());
    }

    private void hitOrStayForAllPlayers() {
        String name = blackjack.nameOfNotBustPlayer();
        while (name != null) {
            hit(name);
            name = blackjack.nameOfNotBustPlayer();
        }
    }

    private void hit(String name) {
        while (blackjack.isNotBust(name) && requestHitOrNot(name)) {
            blackjack.hitByName(name);
            printCurrentStatus(blackjack.generateCurrentCardsDTOByName(name));
        }
    }

    private void addCardForDealerIfNeed() {
        blackjack.addCardForDealerIfNeed();
        if (blackjack.didDealerAdded()) {
            printDealerAdded(blackjack.getDealerName());
        }
    }
}
