package blackjack.domain;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BlackJackReferee {

    private final ParticipantResults participantResults;
    private final BlackJackRule blackJackRule;

    private BlackJackReferee(final ParticipantResults participantResults, final BlackJackRule blackJackRule) {
        this.participantResults = participantResults;
        this.blackJackRule = blackJackRule;
    }

    public static BlackJackReferee from(final int dealerScore) {
        return new BlackJackReferee(new ParticipantResults(), new BlackJackRule(dealerScore));
    }

    public void calculateResult(final Player player) {
        final int playerScore = player.currentScore();
        final ResultType resultType = blackJackRule.calculateDealerResult(playerScore);
        participantResults.addPlayerResult(player.getName(), resultType);
    }

    public Map<String, ResultType> getResult() {
        return participantResults.getPlayerNameToResultType();
    }

    private static class ParticipantResults {

        private final Map<String, ResultType> playerNameToResultType = new HashMap<>();

        public void addPlayerResult(final String playerName, final ResultType resultType) {
            playerNameToResultType.put(playerName, resultType);
        }

        Map<String, ResultType> getPlayerNameToResultType() {
            return playerNameToResultType;
        }
    }
}
