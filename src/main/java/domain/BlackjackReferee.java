package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackReferee {
    public Map<Participant, GameResultStatus> judge(Participant dealer, List<Participant> participants) {
        int dealerSum = dealer.calculateCardsSum();
        return participants.stream()
                .collect(Collectors.toMap(
                        participant -> participant,
                        participant -> GameResultStatus.calculate(dealerSum, participant.calculateCardsSum())
                ));
    }
}
