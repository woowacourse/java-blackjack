package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.participant.Player;
import blackjack.dto.CurrentCardsDTO;

import static blackjack.view.InputView.inputNames;
import static blackjack.view.InputView.requestHitOrStay;
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
        Player player = blackjack.getPlayerWhoCanHit();
        while (player != null) {
            hitOrStay(player);
            player = blackjack.getPlayerWhoCanHit();
        }
    }

    private void hitOrStay(Player player) {
        while (!player.isBust() && requestHitOrStay(player.getName())) {
            blackjack.hit(player);
            printCurrentStatus(new CurrentCardsDTO(player));
        }
    }

    private void addCardForDealerIfNeed() {
        blackjack.addCardForDealerIfNeed();
        if (blackjack.didDealerAdded()) {
            printDealerAdded(blackjack.getDealerName());
        }
    }
}
