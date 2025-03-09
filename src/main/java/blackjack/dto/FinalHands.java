package blackjack.dto;

import java.util.List;

public record FinalHands(
        HandState dealerHand,
        List<HandState> playerHand
) {

}
