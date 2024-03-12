package controller;

import controller.dto.JudgeResult;
import controller.dto.ParticipantHandStatus;
import domain.constants.CardCommand;
import domain.game.ActionAfterPick;
import domain.game.BlackJackGame;
import domain.game.DecisionToContinue;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class GameManager {
    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = start();
        rotate(blackJackGame);
        finish(blackJackGame);
    }

    private BlackJackGame start() {
        BlackJackGame blackJackGame = BlackJackGame.from(inputView.enterPlayerNames());
        outputView.printInitialHandStatus(blackJackGame.initialize());
        return blackJackGame;
    }

    private void rotate(final BlackJackGame blackJackGame) {
        List<Participant> participants = blackJackGame.getParticipants();
        for (Participant participant : participants) {
            blackJackGame.giveCard(
                    participant,
                    getActionAfterPick(participant),
                    getDecisionToContinue(participant)
            );
        }
    }

    private ActionAfterPick getActionAfterPick(final Participant participant) {
        if (participant instanceof Player) {
            return outputView::printHandStatus;
        }
        return handStatus -> outputView.printDealerCardSavedMessage();
    }

    private DecisionToContinue getDecisionToContinue(final Participant participant) {
        if (participant instanceof Player) {
            return () -> CardCommand.from(
                    inputView.requestCommandWhetherGetMoreCard(participant.getName())
            );
        }
        return () -> CardCommand.HIT;
    }

    private void finish(final BlackJackGame blackJackGame) {
        List<ParticipantHandStatus> participantHandStatuses = blackJackGame.createHandStatuses();
        outputView.printResultHandStatus(participantHandStatuses);

        JudgeResult judgeResult = blackJackGame.judge();
        outputView.printJudgeResult(judgeResult);
    }
}
