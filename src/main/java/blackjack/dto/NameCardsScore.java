package blackjack.dto;

import blackjack.model.deck.Card;
import blackjack.model.participant.Name;
import java.util.List;

public record NameCardsScore(Name name, List<Card> cards, int score) {
}
