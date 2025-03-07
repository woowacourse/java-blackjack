package blackjack.dto;

import java.util.List;

public record HandsAfterDrawingCard(
        HandState dealerHand,
        List<HandState> playerHand
) {

}
