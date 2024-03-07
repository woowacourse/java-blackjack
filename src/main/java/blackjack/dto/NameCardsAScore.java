package blackjack.dto;

import blackjack.model.Card;
import java.util.List;

public record NameCardsAScore(String name, List<Card> cards, int score) {

}
