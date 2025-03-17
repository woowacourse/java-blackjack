package game;

import ScoreResult.ScoreBoard;
import bank.PlayerBettingRecord;
import participant.Participant;
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
        final PlayerBettingRecord bettingStatement = new PlayerBettingRecord(inputView.askBettingMoney(blackJackGame));
        blackJackGame.drawTwoCards();
        outputView.writeDrawTwoCards(blackJackGame);
        for (Participant participant : blackJackGame.getOnlyPlayers()) {
            drawOneCard(participant, blackJackGame);
        }
        outputView.writeDealerDrawOneCard(blackJackGame.drawDealer());
        outputView.writeParticipantsScoreResult(blackJackGame);
        outputView.writeTotalProfit(bettingStatement.calculateProfit(new ScoreBoard(blackJackGame.getParticipants())));
    }

    private void drawOneCard(Participant participant, BlackJackGame blackJackGame) {
        while (participant.ableToDraw() && inputView.askDrawOneMore(participant)) {
            blackJackGame.drawOneCards(participant);
            outputView.writePlayerDrawOneCard(participant);
        }
    }
}
