package blackjack.domain;

import static blackjack.domain.PlayStatus.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;

public enum PlayRecord {

    WIN,
    PUSH,
    LOSS,
    BLACKJACK;

    public static Map<Name, PlayRecord> createPlayRecords(List<Player> players, int dealerScore) {
        return List.copyOf(players).stream()
            .collect(toUnmodifiableMap(Player::getName,
                player -> of(dealerScore, player.getScore(), player.isBlackjack())));
    }

    private static PlayRecord of(int dealerScore, int score, boolean isBlackjack) {
        if (isPlayerLoss(dealerScore, score)) {
            return LOSS;
        }

        if (dealerScore == score) {
            return PUSH;
        }

        if (isBlackjack) {
            return BLACKJACK;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(int dealerScore, int score) {
        return isBust(score) || (!isBust(dealerScore) && score < dealerScore);
    }
}
