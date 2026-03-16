package domain.card;

import java.util.List;

public record CurrentHands(
        CurrentHand dealerHand,
        List<CurrentHand> playerHands
) {
}
