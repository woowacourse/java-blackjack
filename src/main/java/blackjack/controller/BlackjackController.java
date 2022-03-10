package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.CurrentCardsDTO;

import static blackjack.view.InputView.inputNames;
import static blackjack.view.InputView.requestHitOrStay;
import static blackjack.view.OutputView.*;

public class BlackjackController {

    public void run() {
        try {
            play();
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage());
        }
    }

    public void play() {
        Blackjack blackjack = new Blackjack(inputNames());
        firstDistribute(blackjack);
        hitOrStayForAllPlayers(blackjack);
        addCardForDealerIfNeed(blackjack);
        printTotalScore(blackjack.generateAllResultDTO());
        printTotalResult(blackjack.calculateTotalResult());
    }

    private void firstDistribute(Blackjack blackjack) {
        blackjack.firstDistribute();
        printFirstDistribute(blackjack.generateAllCurrentCardsDTO());
    }

    private void hitOrStayForAllPlayers(Blackjack blackjack) {
        Player player = blackjack.getPlayerWhoCanHit();
        while (player != null) {
            hitOrStay(blackjack, player);
            player = blackjack.getPlayerWhoCanHit();
        }
    }

    private void hitOrStay(Blackjack blackjack, Player player) {
        while (!player.isBust() && requestHitOrStay(player.getName())) {
            blackjack.hit(player);
            printCurrentStatus(new CurrentCardsDTO(player));
        }
    }

    private void addCardForDealerIfNeed(Blackjack blackjack) {
        blackjack.addCardForDealerIfNeed();
        if (blackjack.didDealerAdded()) {
            printDealerAdded(Dealer.NAME_OF_DEALER);
        }
    }
}
