package domain.dto;

import domain.Card;
import java.util.List;

public record NameAndCards(
        String name,
        List<Card> cards
) {

}
