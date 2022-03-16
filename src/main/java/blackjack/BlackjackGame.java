package blackjack;

import blackjack.domain.BlackjackBoard;
import blackjack.domain.DrawCommand;
import blackjack.domain.GameResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.HoldingCard;
import blackjack.domain.participant.Player;
import blackjack.dto.ScoreResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final BlackjackBoard blackjackBoard;

    public BlackjackGame() {
        this.blackjackBoard = new BlackjackBoard(CardDeck.createNewCardDeck(), initializePlayers());
    }

    public void start() {
        OutputView.printInitialCardStatus(blackjackBoard.getParticipantsDto());

        runAllPlayersTurn();
        OutputView.printPlayerFinalCards(blackjackBoard.getParticipantsDto());
        OutputView.printFinalScore(createFinalScore());
    }

    private List<Player> initializePlayers() {
        try {
            return InputView.askPlayerNames().stream()
                    .map(Player::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initializePlayers();
        }
    }

    private void runAllPlayersTurn() {
        runPlayerTurn();
        runDealerTurn();
    }

    private void runPlayerTurn() {
        if (blackjackBoard.isAllPlayerFinished()) {
            return;
        }
        String currentPlayerName = blackjackBoard.getCurrentPlayerName();
        HoldingCard currentPlayerCards = blackjackBoard.drawCurrentPlayer(askDrawCommand(currentPlayerName));
        OutputView.printPlayerCards(currentPlayerName, currentPlayerCards);
        runPlayerTurn();
    }

    private void runDealerTurn() {
        int gainCardCount = blackjackBoard.dealerFinishGame();
        OutputView.printDealerGainCardCount(gainCardCount);
    }

    private DrawCommand askDrawCommand(String name) {
        try {
            return DrawCommand.from(InputView.askDrawCommand(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askDrawCommand(name);
        }
    }

    private ScoreResultDto createFinalScore() {
        Map<GameResult, Integer> dealerResult = blackjackBoard.getDealerResult();
        Map<String, GameResult> playersResult = blackjackBoard.getPlayersResult();
        return ScoreResultDto.from(dealerResult, playersResult);
    }
}
