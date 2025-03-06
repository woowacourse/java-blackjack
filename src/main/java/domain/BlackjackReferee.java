package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackReferee {
    public GameResult judge(Dealer dealer, List<Player> tempParticipants) {
        int dealerSum = dealer.calculateCardsSum();
        Map<Player, GameResultStatus> gameResult = tempParticipants.stream()
                .collect(Collectors.toMap(
                        participant -> participant,
                        participant -> GameResultStatus.calculate(dealerSum, participant.calculateCardsSum())
                ));
        return new GameResult(gameResult);
    }
}
