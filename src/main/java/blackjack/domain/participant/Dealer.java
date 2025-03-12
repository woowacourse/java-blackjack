package blackjack.domain.participant;

import blackjack.domain.ResultStatus;
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
    public boolean canGetMoreCard() {
        int score = hand.calculateMinScore();
        return score <= DEALER_THRESHOLD;
    }

    public Map<String, ResultStatus> calculateWinningResult(final Map<String, Integer> playerScores) {
        int dealerScore = calculateMaxScore();
        return playerScores.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> ResultStatus.calculateResultStatus(entry.getValue(), dealerScore)));
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
