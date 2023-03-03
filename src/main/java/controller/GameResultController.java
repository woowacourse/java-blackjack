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
        Participant dealer = participants.getDealer();
        printParticipantCardResult(dealer);
        List<Participant> players = participants.getPlayer();
        players.forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        List<Card> participantCards = participant.getCard();
        int participantScore = participant.calculateScore();
        outputView.printCardResult(participant.getName(), participantCards, participantScore);
    }

    private void printFinalGameResult(final Participants participants) {
        GameResult gameResult = GameResult.create(participants);
        Map<String, Result> playerGameResults = gameResult.getPlayerGameResults();
        Participant dealer = participants.getDealer();
        outputView.printFinalGameResult(dealer.getName(), playerGameResults);
    }
}
