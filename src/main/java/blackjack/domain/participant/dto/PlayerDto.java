package blackjack.domain.participant.dto;

import blackjack.domain.card.Card;
import java.util.List;

public record PlayerDto(String name, List<Card> cards) {
}
