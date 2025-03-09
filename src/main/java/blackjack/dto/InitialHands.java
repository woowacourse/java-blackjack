package blackjack.dto;

import java.util.List;

public record InitialHands(
        HiddenDealerHandState dealerHand,
        List<HandState> playerHand
) {

}
