package controller;

import controller.dto.request.PlayerBettingMoney;
import controller.dto.response.ParticipantHandStatus;
import controller.dto.response.ParticipantProfitResponse;
import domain.constants.CardCommand;
import domain.game.ActionAfterPick;
import domain.game.BlackJackGame;
import domain.game.DecisionToContinue;
import domain.game.deck.RandomDeckGenerator;
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
        BlackJackGame blackJackGame = BlackJackGame.from(
                generatePlayerBettingMoney(),
                new RandomDeckGenerator()
        );
        outputView.printInitialHandStatus(blackJackGame.initialize());
        return blackJackGame;
    }

    private List<PlayerBettingMoney> generatePlayerBettingMoney() {
        List<String> playerNames = inputView.enterPlayerNames();
        return playerNames.stream()
                .map(playerName -> new PlayerBettingMoney(
                        playerName,
                        inputView.enterBettingMoney(playerName))
                )
                .toList();
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
                    inputView.requestCommandWhetherGetMoreCard(participant.name())
            );
        }
        return () -> CardCommand.HIT;
    }

    private void finish(final BlackJackGame blackJackGame) {
        List<ParticipantHandStatus> participantHandStatuses = blackJackGame.createHandStatuses();
        outputView.printResultHandStatus(participantHandStatuses);

        List<ParticipantProfitResponse> responses = blackJackGame.judge();
        outputView.printParticipantProfitResponse(responses);
    }
}
