package blackjack.dto;

import static java.util.stream.Collectors.joining;

import blackjack.constant.MatchResult;
import blackjack.domain.ScoreBoard;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class ScoreBoardResponse {

    private static final String SPACE_DELIMINATOR = " ";
    private static final String COLON_DELIMINATOR = ": ";
    private static final String DEALER_MATCH_RESULT_MESSAGE = "딜러: ";

    private String dealerMatchResultMessage;
    private List<String> playerMatchResultMessages = new LinkedList<>();

    private ScoreBoardResponse(ScoreBoard scoreBoard) {
        makeDealerMatchResultMessage(scoreBoard);
        makePlayerMatchResultMessage(scoreBoard);
    }

    public static ScoreBoardResponse from(ScoreBoard scoreBoard) {
        return new ScoreBoardResponse(scoreBoard);
    }

    private void makeDealerMatchResultMessage(ScoreBoard scoreBoard) {
        dealerMatchResultMessage = DEALER_MATCH_RESULT_MESSAGE + scoreBoard.getDealerMatchResults().entrySet()
                .stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> entry.getValue() + entry.getKey().getName())
                .collect(joining(SPACE_DELIMINATOR));
    }

    private void makePlayerMatchResultMessage(ScoreBoard scoreBoard) {
        for (Entry<String, MatchResult> entry : scoreBoard.getPlayersMatchResult().entrySet()) {
            String playerName = entry.getKey();
            String matchResultName = entry.getValue().getName();
            playerMatchResultMessages.add(playerName + COLON_DELIMINATOR + matchResultName);
        }
    }

    public String getDealerMatchResultMessage() {
        return dealerMatchResultMessage;
    }

    public List<String> getPlayerMatchResultMessages() {
        return playerMatchResultMessages;
    }
}
