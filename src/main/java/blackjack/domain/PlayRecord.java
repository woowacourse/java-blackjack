package blackjack.domain;

import static blackjack.domain.PlayStatus.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public enum PlayRecord {

    WIN,
    PUSH,
    LOSS,
    BLACKJACK;

    public static Map<Name, PlayRecord> createPlayRecords(List<Player> players, Dealer dealer) {
        return List.copyOf(players).stream()
            .collect(toUnmodifiableMap(Player::getName,
                player -> of(dealer, player)));
    }

    private static PlayRecord of(Dealer dealer, Player player) {
        if (isPlayerLoss(dealer, player)) {
            return LOSS;
        }

        if (dealer.getScore() == player.getScore() && !dealer.isBlackjack()) {
            return PUSH;
        }

        if (player.isBlackjack()) {
            return BLACKJACK;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(Dealer dealer, Player player) {
        return isBust(player.getScore()) || (!isBust(dealer.getScore()) && player.getScore() < dealer.getScore())
            || (dealer.isBlackjack() && !player.isBlackjack());
    }
}
