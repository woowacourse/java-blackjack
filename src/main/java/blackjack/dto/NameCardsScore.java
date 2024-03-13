package blackjack.dto;

import blackjack.model.deck.Card;
import java.util.List;

public record NameCardsScore(String name, List<Card> cards, int score) {
}
