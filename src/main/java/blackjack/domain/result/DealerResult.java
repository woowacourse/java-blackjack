package blackjack.domain.result;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.User;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<UserResult, Integer> count;

    public DealerResult(Participants participants) {
        this.count = new EnumMap<>(UserResult.class);
        calculateFinalCount(participants);
    }

    private void calculateFinalCount(Participants participants) {
        Participant dealer = participants.getDealer();
        for (User user : participants.getUsers()) {
            UserResult userResult = user.checkResult(dealer);
            addCount(UserResult.swap(userResult));
        }
    }

    private void addCount(UserResult result) {
        count.put(result, count.getOrDefault(result, 0) + 1);
    }

    public Map<UserResult, Integer> getCount() {
        return Collections.unmodifiableMap(count);
    }
}
