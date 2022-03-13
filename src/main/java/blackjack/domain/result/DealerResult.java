package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Participants;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<Result, Integer> count;

    public DealerResult(Participants participants) {
        this.count = new EnumMap<>(Result.class);
        calculateFinalCount(participants);
    }

    private void calculateFinalCount(Participants participants) {
        int dealerSum = participants.getDealer().getCardSum();
        for (User user : participants.getUsers()) {
            Result userResult = user.checkResult(dealerSum);
            addCount(Result.swap(userResult));
        }
    }

    private void addCount(Result result) {
        count.put(result, count.getOrDefault(result, 0) + 1);
    }

    public Map<Result, Integer> getCount() {
        return Collections.unmodifiableMap(count);
    }
}
