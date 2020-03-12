package controller;

import java.util.Map;

import domain.Players;
import domain.Result;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.User;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    public static void run() {
        String names = InputView.inputUserNames();
        Players participants = new Players(names);
        OutputView.initialSetting(participants);

        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        dealer.firstDraw(cardDeck);
        participants.firstDraw(cardDeck);

        OutputView.printDealerFirstDraw(dealer);
        OutputView.printCardStatusForAllParticipants(participants);

        for (User user : participants.getPlayers()) {
            while (InputUtils.isHit(InputView.inputIsHit(user))) {
                user.receive(cardDeck);
                OutputView.printCardStatus(user);
            }
        }
        dealerHit(dealer, cardDeck);
        OutputView.printFinalScoreForAllParticipants(dealer, participants);

        Map<String, Result> userResultMap = participants.putResultIntoMap(dealer);
        OutputView.printFinalResult();
        OutputView.printDealerResult(userResultMap);
        for (Map.Entry<String, Result> userResultEntry : userResultMap.entrySet()) {
            OutputView.printUserResult(userResultEntry);
        }
    }

    static void dealerHit(Dealer dealer, CardDeck cardDeck) {
        while (dealer.calculateScore() <= 16) {
            OutputView.printDealerAdditionalCard();
            dealer.receive(cardDeck);
        }
    }

}
