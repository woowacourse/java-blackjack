package blackjack;

import card.DeckGenerator;
import player.Player;
import view.InputView;
import view.OutputView;

public class ConsoleBlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleBlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Blackjack blackjack = new Blackjack(inputView.inputParticipantName(), DeckGenerator.generateDeck());

        blackjack.distributeInitialCards();
        openInitialCards(blackjack);
        addMoreCards(blackjack);

        outputView.printPlayersCardsAndSum(blackjack.getDealer(),
                blackjack.getParticipants(), blackjack.getNameAndSumOfAllPlayers());
        outputView.printMatchResult(blackjack.computeDealerMatchResult(), blackjack.computeParticipantsMatchResult());
    }

    private void openInitialCards(Blackjack blackjack) {
        outputView.printOpenInitialCards(blackjack.getDealer(), blackjack.getParticipants());
        outputView.printInitialCards(blackjack.openInitialCards());
    }

    private void addMoreCards(Blackjack blackjack) {
        for (Player participant : blackjack.getParticipants()) {
            addMoreCardsIfNotBust(blackjack, participant);
        }
        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            outputView.printAddCardToDealer();
        }
    }

    private void addMoreCards(Blackjack blackjack, Player participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(inputView.inputWantOneMoreCard(participant.getName()));
            addOneCardIfYes(blackjack, participant, yesOrNo);
            outputView.printPlayerCards(participant.getName(), participant.getCards());
        } while (yesOrNo.equals(YesOrNo.YES) && !blackjack.isBust(participant));
    }

    private void addOneCardIfYes(Blackjack blackjack, Player participant, YesOrNo yesOrNo) {
        if (yesOrNo.equals(YesOrNo.YES)) {
            blackjack.addCard(participant);
        }
    }

    private void addMoreCardsIfNotBust(Blackjack blackjack, Player participant) {
        addMoreCards(blackjack, participant);
    }
}
