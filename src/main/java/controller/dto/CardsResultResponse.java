package controller.dto;

import domain.Card;
import java.util.List;

public record CardsResultResponse(
        String name,
        List<Card> cards,
        int handValue
) {

}
