package dto;

import domain.card.Card;
import java.util.List;

public record MemberStatus(
        String memberName,
        List<Card> cards,
        int totalValue
) {
}
