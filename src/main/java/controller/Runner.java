package controller;

import domain.card.RandomCardGenerator;
import domain.game.BlackjackGame;

import domain.player.Participant;
import view.InputView;
import view.OutputView;

import java.util.List;

public final class Runner {

    private final InputView inputView;
    private final OutputView outputView;

    public Runner(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackjackGame blackjackGame = joinGame();
        startGame(blackjackGame);
        playGame(blackjackGame);
        judgeGameResult(blackjackGame);
    }

    private BlackjackGame joinGame() {
        final List<String> participantNames = this.inputView.readPlayerNames();
        this.outputView.printSetupGame(participantNames);

        return BlackjackGame.from(participantNames, this.inputView::readBetAmount, new RandomCardGenerator());
    }

    private void startGame(final BlackjackGame blackjackGame) {
        blackjackGame.drawCards();
        this.outputView.printDrawCards(blackjackGame.getDealerDTO(), blackjackGame.getParticipantDTOs());
    }

    private void playGame(final BlackjackGame blackjackGame) {
        blackjackGame.playParticipantsTurn(this::isHit, outputView::printPlayerCard);
        blackjackGame.playDealerTurn(outputView::printHitOrStay);
    }

    private void judgeGameResult(final BlackjackGame blackjackGame) {
        this.outputView.printGameResult(blackjackGame.getDealerResultDTO(), blackjackGame.getFinalResultDTO());
    }

    private boolean isHit(final Participant participant) {
        return this.inputView.readCommand(participant.getName()).isValue();
    }
}
