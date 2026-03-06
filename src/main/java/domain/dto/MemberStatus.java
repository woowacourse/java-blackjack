package domain.dto;

import domain.Card;
import java.util.List;

public record MemberStatus(
        String playerName,
        List<Card> cards,
        int totalValue
) {
}
