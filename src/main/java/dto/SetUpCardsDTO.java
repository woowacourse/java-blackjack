package dto;

import domain.card.Card;
import java.util.List;
import java.util.Map;

public record SetUpCardsDTO(Card dealerOpenCard, Map<String, List<Card>> cards) {

}
