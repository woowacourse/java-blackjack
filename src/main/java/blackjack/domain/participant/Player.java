package blackjack.domain.participant;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;

import blackjack.domain.card.Hand;
import java.util.ArrayList;
import java.util.Objects;

public final class Player extends Gamer {

    private final String nickname;

    public Player(final String nickname, final Hand hand) {
        super(hand);
        this.nickname = nickname;
    }

    public static Player createEmpty(final String nickname) {
        return new Player(nickname, new Hand(new ArrayList<>()));
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
