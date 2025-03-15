package Blackjack.dto;

import Blackjack.domain.card.Card;
import java.util.List;

public record ParticipantCardsDto(String name, List<Card> cards, int cardsScore) {
}
