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

    /**
     * 게임의 참가자를 생성하고 2장 씩 나누어주어 게임을 초기 세팅하는 메서드
     */
    private BlackJackGame start() {
        BlackJackGame blackJackGame = BlackJackGame.from(inputView.enterPlayerNames());
        outputView.printInitialHandStatus(blackJackGame.initialize());
        return blackJackGame;
    }

    /**
     * 딜러와 플레이어에게 돌아가며 카드를 나누어주는 메서드
     */
    private void rotate(final BlackJackGame blackJackGame) {
        List<Participant> participants = blackJackGame.getParticipants();
        for (Participant participant : participants) {
            blackJackGame.giveCard(
                    participant,
                    getAction(participant),
                    getSupplier(participant)
            );
        }
    }

    private ActionAfterPick getAction(final Participant participant) {
        if (participant instanceof Player) {
            return handStatus -> outputView.printHandStatus(handStatus);
        }
        return handStatus -> outputView.printDealerCardSavedMessage();
    }

    private DecisionToContinue getSupplier(final Participant participant) {
        if (participant instanceof Player) {
            return () -> CardCommand.from(
                    inputView.requestCommandWhetherGetMoreCard(participant.getName())
            );
        }
        return () -> CardCommand.HIT;
    }

    /**
     * 게임의 결과를 출력하고 최종 승패를 출력하는 메서드
     */
    private void finish(final BlackJackGame blackJackGame) {
        List<ParticipantHandStatus> participantHandStatuses = blackJackGame.createHandStatuses();
        outputView.printResultHandStatus(participantHandStatuses);

        JudgeResult judgeResult = blackJackGame.judge();
        outputView.printJudgeResult(judgeResult);
    }
}
