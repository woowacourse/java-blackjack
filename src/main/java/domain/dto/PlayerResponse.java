package domain.dto;

import domain.Card;
import java.util.List;

public record PlayerResponse(
        String name,
        List<Card> cards
) {

}