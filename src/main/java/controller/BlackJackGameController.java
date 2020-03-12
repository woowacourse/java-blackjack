package controller;

import java.util.Map;

import domain.Participant;
import domain.Result;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    public static void run() {
        String names = InputView.inputUserNames();
        Participant participants = new Participant(names);
        OutputView.initialSetting(participants);

        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        dealer.firstDraw(cardDeck);
        participants.firstDraw(cardDeck);

        OutputView.printDealerFirstDraw(dealer);
        OutputView.printCardStatusForAllParticipants(participants);

        for (User user : participants.getParticipants()) {
            while (InputUtils.isHit(InputView.inputIsHit(user))) {
                user.receive(cardDeck);
                OutputView.printCardStatus(user);
            }
        }
        dealerHit(dealer, cardDeck);
        OutputView.printFinalScoreForAllParticipants(dealer, participants);

        Map<String, Result> userResultMap = participants.putResultIntoMap(dealer);

        OutputView.printFinalResult();
        int dealerWin = 0;
        int dealerDraw = 0;
        int dealerLose = 0;
        for (Result value : userResultMap.values()) {
            if (value == Result.승) {
                dealerLose++;
            }
            if (value == Result.무) {
                dealerDraw++;
            }
            if (value == Result.패) {
                dealerWin++;
            }
        }
        OutputView.printDealerResult(dealerWin, dealerDraw, dealerLose);

        for (Map.Entry<String, Result> userResultEntry : userResultMap.entrySet()) {
            OutputView.printUserResult(userResultEntry);
        }

    }

    public static void dealerHit(Dealer dealer, CardDeck cardDeck) {
        while (dealer.calculateScore() <= 16) {
            OutputView.printDealerAdditionalCard();
            dealer.receive(cardDeck);
        }
    }

}
