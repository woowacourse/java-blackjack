package blackjack.dto;

import java.util.List;

public record HandsBeforeDrawingCard(
        HiddenDealerHandState dealerHand,
        List<HandState> playerHand
) {

}
