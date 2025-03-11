package controller;

import domain.Blackjack;
import domain.DeckGenerator;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Blackjack blackjack = new Blackjack(InputView.inputParticipantName(), DeckGenerator.generateDeck());

        blackjack.distributeInitialCards();
        openInitialCards(blackjack);
        addMoreCards(blackjack);

        OutputView.printPlayersCardsAndSum(blackjack.getDealer(),
                blackjack.getParticipants(), blackjack.getNameAndSumOfAllPlayers());
        OutputView.printMatchResult(blackjack.computeDealerMatchResult(), blackjack.computeParticipantsMatchResult());
    }

    private void openInitialCards(Blackjack blackjack) {
        blackjack.openDealerCards();
        OutputView.printInitialCards(blackjack.getDealer(), blackjack.getParticipants());
        blackjack.getPlayers().forEach(OutputView::printPlayerCards);
    }

    private void addMoreCards(Blackjack blackjack) {
        for (Player participant : blackjack.getParticipants()) {
            addMoreCards(blackjack, participant);
        }
        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            OutputView.printAddCardToDealer();
        }
    }

    private void addMoreCards(Blackjack blackjack, Player participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(InputView.inputWantOneMoreCard(participant.getName()));
            addOneCardIfYes(blackjack, participant, yesOrNo);
            OutputView.printPlayerCards(participant);
        } while (yesOrNo.equals(YesOrNo.YES));
    }

    private void addOneCardIfYes(Blackjack blackjack, Player participant, YesOrNo yesOrNo) {
        if (yesOrNo.equals(YesOrNo.YES)) {
            blackjack.addCard(participant);
        }
    }
}
