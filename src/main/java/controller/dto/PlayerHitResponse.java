package controller.dto;

import domain.Card;
import java.util.List;

public record PlayerHitResponse(
        String playerName,
        List<Card> cards
) {

}
