package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Participants;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    Map<Result, Integer> count;

    public DealerResult(Participants users, Dealer dealer) {
        this.count = new EnumMap<>(Result.class);
        calculateFinalCount(users, dealer);
    }

    private void calculateFinalCount(Participants users, Dealer dealer) {
        int dealerSum = dealer.getCardSum();
        for (User user : users.getUsers()) {
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
