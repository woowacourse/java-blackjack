package blackjack.domain.result;

import blackjack.domain.player.Player;

public enum BlackJackResult {

    BLACK_JACK;

    public static BlackJackResult findBlackJackResult(final Player player) {
        return BLACK_JACK;
    }
}
