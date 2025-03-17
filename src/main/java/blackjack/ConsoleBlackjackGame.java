package blackjack;

import bet.BetManager;
import card.DeckGenerator;
import player.Participant;
import match.MatchResults;
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
        BetManager betManager = new BetManager();

        addInitialBet(blackjack, betManager);
        blackjack.distributeInitialCards();
        openInitialCards(blackjack);
        addMoreCards(blackjack);

        MatchResults matchResults = new MatchResults(blackjack.getDealer(), blackjack.getParticipants());
        outputView.printPlayersCardsAndSum(blackjack.getDealer(),
                blackjack.getParticipants(), blackjack.getNameAndSumOfAllPlayers());
        calculateBettingResult(matchResults, betManager);
    }

    private void addInitialBet(Blackjack blackjack, BetManager betManager) {
        for (Participant participant : blackjack.getParticipants()) {
            betManager.addInitialBet(participant, inputView.inputBetAmount(participant.getName()));
        }
    }

    private void openInitialCards(Blackjack blackjack) {
        outputView.printOpenInitialCards(blackjack.getParticipants());
        outputView.printInitialCards(blackjack.openDealerInitialCards(), blackjack.openParticipantsInitialCards());
    }

    private void addMoreCards(Blackjack blackjack) {
        for (Participant participant : blackjack.getParticipants()) {
            addMoreCardsIfNotBust(blackjack, participant);
        }
        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            outputView.printAddCardToDealer();
        }
    }

    private void addMoreCards(Blackjack blackjack, Participant participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(inputView.inputWantOneMoreCard(participant.getName()));
            addOneCardIfYes(blackjack, participant, yesOrNo);
            outputView.printPlayerCards(participant.getName(), participant.getCards());
        } while (yesOrNo.equals(YesOrNo.YES) && !participant.isBust());
    }

    private void addOneCardIfYes(Blackjack blackjack, Participant participant, YesOrNo yesOrNo) {
        if (yesOrNo.equals(YesOrNo.YES)) {
            blackjack.addCardToParticipant(participant);
        }
    }

    private void addMoreCardsIfNotBust(Blackjack blackjack, Participant participant) {
        addMoreCards(blackjack, participant);
    }

    private void calculateBettingResult(MatchResults matchResults, BetManager betManager) {
        betManager.calculateParticipantBetResults(matchResults.getMatchResults());
        outputView.printBettingResult(betManager.calculateDealerBetResultAmount(), betManager.getBetResults());
    }
}
