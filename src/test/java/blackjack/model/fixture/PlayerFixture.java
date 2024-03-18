package blackjack.model.fixture;

import static blackjack.model.fixture.HandFixture.BLACKJACK_HAND;
import static blackjack.model.fixture.HandFixture.BUST_HAND;
import static blackjack.model.fixture.HandFixture.NOT_BLACKJACK_BUT_21_HAND;
import static blackjack.model.fixture.HandFixture.UNDER_16_HAND;

import blackjack.model.participant.Player;

public enum PlayerFixture {

    BLACKJACK_PLAYER(Player.of("블랙잭 플레이어", BLACKJACK_HAND.getHand())),
    BUST_PLAYER(Player.of("버스트 플레이어", BUST_HAND.getHand())),
    NOT_BLACKJACK_21_PLAYER(Player.of("블랙잭 아닌 21인 플레이어", NOT_BLACKJACK_BUT_21_HAND.getHand())),
    UNDER_21_PLAYER(Player.of("21 미만인 플레이어", UNDER_16_HAND.getHand())),
    ;

    private final Player player;

    PlayerFixture(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
