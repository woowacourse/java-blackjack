package dto;

import java.util.List;

public record BlackJackInitStatusDto(HandDto dealerHand,
                                     List<HandDto> playerHands) {
}
