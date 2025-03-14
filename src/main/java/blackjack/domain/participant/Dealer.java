package blackjack.domain.participant;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;
import static blackjack.domain.result.ResultStatus.LOSE;
import static blackjack.domain.result.ResultStatus.PUSH;
import static blackjack.domain.result.ResultStatus.WIN;

import blackjack.domain.card.Hand;
import blackjack.domain.result.DealerWinningResult;
import blackjack.domain.result.ResultStatus;
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
                        entry -> calculateResultStatus(dealerScore, entry.getValue())));
        return new DealerWinningResult(result);
    }

    private ResultStatus calculateResultStatus(final int dealerScore, final int playerScore) {
        if (dealerScore <= BURST_THRESHOLD) {
            return calculateResultStatusUnderBlackjackNumber(dealerScore, playerScore);
        }
        if (playerScore > BURST_THRESHOLD) {
            return WIN;
        }
        return LOSE;
    }

    private ResultStatus calculateResultStatusUnderBlackjackNumber(final int dealerScore, final int playerScore) {
        if (playerScore <= BURST_THRESHOLD) {
            return calculateResultStatusBothUnderBlackjackNumber(dealerScore, playerScore);
        }
        return WIN;
    }

    private ResultStatus calculateResultStatusBothUnderBlackjackNumber(final int dealerScore, final int playerScore) {
        if (dealerScore > playerScore) {
            return WIN;
        }
        if (dealerScore == playerScore) {
            return PUSH;
        }
        return LOSE;
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
