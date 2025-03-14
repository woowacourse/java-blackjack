package blackjack.controller;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.result.ParticipantResults;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final BlackJackInitManager blackJackInitManager;
    private final BlackjackProcessManager blackjackProcessManager;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, BlackJackInitManager blackJackInitManager) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.blackJackInitManager = blackJackInitManager;
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck(),
                new ParticipantResults());
    }

    public void run() {
        List<String> names = InputView.readNames();

        Participants participants = blackJackInitManager.saveParticipants(names);

        giveStartingCards(participants);
        participants.getParticipants().forEach(this::giveMoreCard);

        ParticipantResults participantResults = calculateResultOfParticipants(participants);

        Participant defender = participants.findDefender();

        OutputView.printCardResult(participantResults);
        OutputView.printGameResult(participantResults);
    }

    private void giveMoreCard(Participant participant) {
        if (participant.canDecideToTakeMoreCard()) {
            takeCardsAsLongAsWanted(participant);
            return;
        }
        takeCardManually(participant);
    }

    private void takeCardsAsLongAsWanted(Participant participant) {
        Confirmation confirmation = InputView.askToGetMoreCard(participant);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(participant);
            return;
        }

        blackjackProcessManager.giveMoreCard(participant);
        OutputView.printCardResult(participant);

        if (gameRuleEvaluator.isBusted(participant)) {
            OutputView.printBustedParticipantWithName(participant);
            return;
        }

        if (participant.ableToTakeMoreCards()) {
            giveMoreCard(participant);
        }
    }

    private void takeCardManually(Participant participant) {
        while (participant.ableToTakeMoreCards()) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveMoreCard(participant);
        }
    }

    private void giveStartingCards(Participants participants) {
        blackjackProcessManager.giveStartingCards(participants);

        OutputView.printStartingCardsStatuses(participants);
    }

    private ParticipantResults calculateResultOfParticipants(Participants participants) {
        blackjackProcessManager.calculateAllResults(participants, gameRuleEvaluator);
        return blackjackProcessManager.getParticipantResults();
    }
}
