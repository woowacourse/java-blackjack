package blackjack.domain.game;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import blackjack.domain.Name;
import blackjack.domain.state.Cards;

public enum PlayRecord {

    WIN,
    PUSH,
    LOSS,
    BLACKJACK;

    static Map<Name, PlayRecord> createPlayRecords(List<Player> players, Dealer dealer) {
        return List.copyOf(players).stream()
            .collect(toUnmodifiableMap(Player::getName,
                player -> of(dealer.getCards(), player.getCards())));
    }

    private static PlayRecord of(Cards dealerCards, Cards playerCards) {
        if (isPlayerLoss(dealerCards, playerCards)) {
            return LOSS;
        }

        if (dealerCards.sum() == playerCards.sum() && !dealerCards.isBlackjack()) {
            return PUSH;
        }

        if (playerCards.isBlackjack()) {
            return BLACKJACK;
        }

        return WIN;
    }

    private static boolean isPlayerLoss(Cards dealerCards, Cards playerCards) {
        return playerCards.isBust() || (!dealerCards.isBust() && playerCards.sum() < dealerCards.sum())
            || (dealerCards.isBlackjack() && !playerCards.isBlackjack());
    }
}
