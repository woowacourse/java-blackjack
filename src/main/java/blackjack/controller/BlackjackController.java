package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.card.Deck;
import blackjack.domain.result.ParticipantResults;
import blackjack.manager.BlackjackGame;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final BlackjackGame blackjackGame;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.blackjackGame = new BlackjackGame(new Deck(), new ParticipantResults());
    }

    public void run() {
        List<String> names = InputView.readNames();

        Participants participants = saveParticipants(names);

        giveStartingCards(participants);
        participants.getParticipants().forEach(this::giveMoreCard);

        ParticipantResults participantResults = calculateResultOfParticipants(participants);

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

        blackjackGame.giveMoreCard(participant);
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
            blackjackGame.giveMoreCard(participant);
        }
    }

    private void giveStartingCards(Participants participants) {
        blackjackGame.giveStartingCards(participants);

        OutputView.printStartingCardsStatuses(participants);
    }

    private ParticipantResults calculateResultOfParticipants(Participants participants) {
        blackjackGame.calculateAllResults(participants, gameRuleEvaluator);
        return blackjackGame.getParticipantResults();
    }

    private Participants saveParticipants(List<String> names) {
        List<Participant> participants = new java.util.ArrayList<>(names.stream()
                .map(name -> (Participant) new Player(name, new Hand()))
                .toList());
        participants.add(new Dealer(new Hand()));
        return new Participants(participants);
    }
}
