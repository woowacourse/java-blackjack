package blackjack;

import blackjack.domain.BettingResult;
import blackjack.domain.BlackjackBoard;
import blackjack.domain.DrawCommand;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.HoldingCard;
import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final BlackjackBoard blackjackBoard;

    public BlackjackGame() {
        this.blackjackBoard = new BlackjackBoard(CardDeck.createNewCardDeck(), initializePlayers());
    }

    public void start() {
        OutputView.printInitialCardStatus(blackjackBoard.getParticipantsDto());
        runAllParticipantsTurn();
        OutputView.printPlayerFinalCards(blackjackBoard.getParticipantsDto());
        OutputView.printFinalScore(createFinalScore());
    }

    private List<Player> initializePlayers() {
        try {
            return InputView.askPlayerNames().stream()
                    .map(String::trim)
                    .map((String name) -> new Player(name, makeBetMoney(name)))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initializePlayers();
        }
    }

    private BetMoney makeBetMoney(final String name) {
        try {
            return new BetMoney(InputView.askBetMoney(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return makeBetMoney(name);
        }
    }

    private void runAllParticipantsTurn() {
        runAllPlayersTurn();
        runDealerTurn();
    }

    private void runAllPlayersTurn() {
        if (blackjackBoard.isAllPlayerFinished()) {
            return;
        }
        String currentPlayerName = blackjackBoard.getCurrentPlayerName();
        HoldingCard currentPlayerCards = blackjackBoard.drawCurrentPlayer(askDrawCommand(currentPlayerName));
        OutputView.printPlayerCards(currentPlayerName, currentPlayerCards);
        runAllPlayersTurn();
    }

    private void runDealerTurn() {
        int gainCardCount = blackjackBoard.dealerFinishGame();
        OutputView.printDealerGainCardCount(gainCardCount);
    }

    private DrawCommand askDrawCommand(final String name) {
        try {
            return DrawCommand.from(InputView.askDrawCommand(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askDrawCommand(name);
        }
    }

    private BettingResult createFinalScore() {
        return blackjackBoard.getBettingResult();
    }
}
