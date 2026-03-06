package domain.dto;

import domain.Card;
import java.util.List;

public record PlayerStatus(
        String playerName,
        List<Card> cards,
        int totalValue
) {
}
