package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ResultStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Dealer extends Participant implements GameRule {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_THRESHOLD = 16;
    private static final int SPREAD_CARD_SIZE = 2;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public Dealer() {
        this(new Hand(new ArrayList<>()));
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

    public void dealInitialCards(final Players players, final Hand hand) {
        receiveCards(hand.subHand(0, SPREAD_CARD_SIZE));
        players.receiveCardsByCount(hand.subHand(SPREAD_CARD_SIZE, hand.getSize()), SPREAD_CARD_SIZE);
    }

    public ProfitResult calculateProfit(final Map<Player, ResultStatus> playerScores) {
        return ProfitResult.from(this, playerScores);
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

    public int getSpreadCardSize() {
        return SPREAD_CARD_SIZE;
    }
}
