package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.CurrentCardsDTO;

import static blackjack.view.InputView.inputNames;
import static blackjack.view.InputView.isRequestHit;
import static blackjack.view.OutputView.*;

public class BlackjackController {

    public void run() {
        try {
            Blackjack blackjack = new Blackjack(inputNames());
            play(blackjack);
            generateAndPrintResult(blackjack);
        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage());
        }
    }

    private void play(Blackjack blackjack) {
        firstDistribute(blackjack);
        hitOrStayForAllPlayers(blackjack);
        addCardForDealerIfNeed(blackjack);
        blackjack.endGame();
    }

    private void generateAndPrintResult(Blackjack blackjack) {
        printTotalScore(blackjack.computeTotalScore());
        printTotalResult(blackjack.computeTotalResult());
    }

    private void firstDistribute(Blackjack blackjack) {
        blackjack.firstDistribute();
        printFirstDistribute(blackjack.generateAllCurrentCardsDTO());
    }

    private void hitOrStayForAllPlayers(Blackjack blackjack) {
        Player player = blackjack.findPlayerWhoCanHit();
        while (player != null) {
            hitOrStay(blackjack, player);
            player = blackjack.findPlayerWhoCanHit();
        }
    }

    private void hitOrStay(Blackjack blackjack, Player player) {
        while (player.isAbleToHit() && isRequestHit(player.getName())) {
            blackjack.hit(player);
            printCurrentStatus(new CurrentCardsDTO(player));
        }
    }

    private void addCardForDealerIfNeed(Blackjack blackjack) {
        blackjack.addCardForDealerIfNeed();
        if (blackjack.isDealerTakeMoreCards()) {
            printDealerAdded(Dealer.NAME_OF_DEALER);
        }
    }
}
