package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.ArrayList;
import java.util.List;

public class Judge {


    public static List<ParticipantResult> calculateGameResult(final Players players) {
        final List<ParticipantResult> participantResults = new ArrayList<>();
        for (Player participant : players.getParticipants()) {
            Result participantResult = Result.competeResult(players.getDealer(), participant);
            participantResults.add(new ParticipantResult(participant, participantResult));
        }

        return participantResults;
    }

    public static void calculateParticipantProfit(final List<ParticipantResult> participantResults) {
        for (ParticipantResult participantResult : participantResults) {
            participantResult.calculate();
        }
    }

    public static Money calculateDealerProfit(final List<ParticipantResult> participantResults) {
        Money money = new Money(0);
        for (ParticipantResult participantResult : participantResults) {
            money.add(participantResult.calculateDealer());
        }
        return money;
    }

}
