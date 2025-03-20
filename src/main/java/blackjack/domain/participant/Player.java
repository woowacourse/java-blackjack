package blackjack.domain.participant;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;

import blackjack.domain.card.Hand;
import blackjack.util.ExceptionMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public final class Player extends Participant {

    private final String nickname;
    private final BigDecimal bettingAmount;

    public Player(final Hand hand, final String nickname, final BigDecimal bettingAmount) {
        super(hand);
        validateBettingAmount(bettingAmount);
        this.nickname = nickname;
        this.bettingAmount = bettingAmount;
    }

    public Player(final String nickname, final BigDecimal bettingAmount) {
        this(new Hand(new ArrayList<>()), nickname, bettingAmount);
    }

    private void validateBettingAmount(final BigDecimal bettingAmount) {
        if (bettingAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.makeMessage("베팅 금액을 양수로 입력해주세요."));
        }
    }

    @Override
    public Hand showInitialCards() {
        return hand;
    }

    @Override
    public boolean canHit() {
        int score = hand.calculateWithHardHand();
        return score < BURST_THRESHOLD;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Player player)) {
            return false;
        }
        return Objects.equals(nickname, player.nickname) && Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, hand);
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public BigDecimal getBettingAmount() {
        return bettingAmount;
    }
}
