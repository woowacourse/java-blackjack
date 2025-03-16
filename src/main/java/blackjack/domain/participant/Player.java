package blackjack.domain.participant;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;

import blackjack.domain.card.Hand;
import java.util.ArrayList;
import java.util.Objects;

public final class Player extends Gamer {

    private final String nickname;
    private final int bettingAmount;

    public Player(final Hand hand, final String nickname, final int bettingAmount) {
        super(hand);
        validate(bettingAmount);
        this.nickname = nickname;
        this.bettingAmount = bettingAmount;
    }

    private void validate(final int bettingAmount) {
        if (bettingAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액을 양수로 입력해주세요.");
        }
    }

    public static Player from(final String nickname, final int bettingAmount) {
        return new Player(new Hand(new ArrayList<>()), nickname, bettingAmount);
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
}
