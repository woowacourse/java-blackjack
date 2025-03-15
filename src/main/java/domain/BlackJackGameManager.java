package domain;

import domain.ScoreResult.ScoreBoard;
import domain.participant.Participant;
import view.InputView;
import view.OutputView;

public class BlackJackGameManager {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final BlackJackGame blackJackGame = new BlackJackGame(inputView.askPlayerNames());
        final GamblingStatement bettingStatement = new GamblingStatement(inputView.askBettingMoney(blackJackGame));
        blackJackGame.drawTwoCards();
        outputView.writeDrawTwoCards(blackJackGame);
        for (Participant participant : blackJackGame.getParticipants().findPlayers().getParticipants()) {
            while (participant.ableToDraw() && inputView.askDrawOneMore(participant)) {
                blackJackGame.drawOneCards(participant);
                outputView.writePlayerDrawOneCard(participant);
            }
        }
        outputView.writeDealerDrawOneCard(blackJackGame.drawDealer());
        outputView.writeParticipantsScoreResult(blackJackGame);
        outputView.writeTotalProfit(bettingStatement.calculateProfit(new ScoreBoard(blackJackGame.getParticipants())));
    }
}
