package controller;

import domain.card.Card;
import domain.game.GameResult;
import domain.game.Result;
import domain.participant.Participant;
import domain.participant.Participants;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class GameResultController {

    private final OutputView outputView;

    private GameResultController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public static GameResultController create(final OutputView outputView) {
        return new GameResultController(outputView);
    }

    public void start(final Participants participants) {
        printGameResult(participants);
        printFinalGameResult(participants);
    }

    private void printGameResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        printParticipantCardResult(dealer);
        final List<Participant> players = participants.getPlayer();
        players.forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        final List<Card> participantCards = participant.getCard();
        final int participantScore = participant.calculateScore();
        outputView.printCardResult(participant.getName(), participantCards, participantScore);
    }

    private void printFinalGameResult(final Participants participants) {
        final GameResult gameResult = GameResult.create(participants);
        final Map<String, Result> playerGameResults = gameResult.getPlayerGameResults();
        final Participant dealer = participants.getDealer();
        outputView.printFinalGameResult(dealer.getName(), playerGameResults);
    }
}
