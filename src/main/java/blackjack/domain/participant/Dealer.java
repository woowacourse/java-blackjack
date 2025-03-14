package blackjack.domain.participant;

import blackjack.domain.result.ResultStatus;
import blackjack.domain.result.DealerWinningResult;
import blackjack.domain.card.Hand;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Dealer extends Gamer {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_THRESHOLD = 16;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public static Dealer createEmpty() {
        return new Dealer(new Hand(new ArrayList<>()));
    }

    @Override
    public Hand showInitialCards() {
        return new Hand(List.of(hand.getFirstCard()));
    }

    @Override
    public boolean canHit() {
        int score = hand.calculateWithHardHand();
        return score <= DEALER_THRESHOLD;
    }

    public DealerWinningResult makeDealerWinningResult(final Map<String, Integer> playerScores) {
        int dealerScore = calculateScore();
        Map<String, ResultStatus> result = playerScores.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> ResultStatus.calculateResultStatus(entry.getValue(), dealerScore)));
        return new DealerWinningResult(result);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Dealer dealer)) {
            return false;
        }
        return Objects.equals(hand, dealer.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hand);
    }

    @Override
    public String getNickname() {
        return NICKNAME;
    }
}
