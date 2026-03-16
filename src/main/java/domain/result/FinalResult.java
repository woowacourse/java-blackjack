package domain.result;

import domain.card.CurrentHand;

public record FinalResult(
        CurrentHand currentHand,
        int score
) {
}
