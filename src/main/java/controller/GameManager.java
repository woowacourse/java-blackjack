package controller;

import controller.dto.HandStatus;
import controller.dto.JudgeResult;
import domain.BlackJackGame;
import domain.Participant;
import domain.Player;
import domain.constants.CardCommand;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
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
        outputView.printInitialStatus(blackJackGame.initialize());
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

    private Consumer<HandStatus> getAction(final Participant participant) {
        if (participant instanceof Player) {
            return handStatus -> outputView.printCardStatus(handStatus);
        }
        return handStatus -> outputView.printDealerPickMessage();
    }

    private Supplier<CardCommand> getSupplier(final Participant participant) {
        if (participant instanceof Player) {
            return () -> CardCommand.from(
                    inputView.decideToGetMoreCard(participant.getName())
            );
        }
        return () -> CardCommand.HIT;
    }

    /**
     * 게임의 결과를 출력하고 최종 승패를 출력하는 메서드
     */
    private void finish(final BlackJackGame blackJackGame) {
        List<HandStatus> handStatuses = blackJackGame.createHandStatuses();
        outputView.printResultHandStatus(handStatuses);

        JudgeResult judgeResult = blackJackGame.judge();
        outputView.printGameResult(judgeResult);
    }
}
